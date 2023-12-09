package com.example.web_ban_cong.controller;

import com.example.web_ban_cong.model.Category;
import com.example.web_ban_cong.model.CategoryTree;
import com.example.web_ban_cong.model.Product;
import com.example.web_ban_cong.repository.ProductRepository;
import com.example.web_ban_cong.service.CategoryService;
import com.example.web_ban_cong.service.CategoryTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryTreeService categoryTreeService;
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/product/add")
    public String addCategory(Model model){
        model.addAttribute("category", new Category());
        return "back-end/add-new-category";
    }
    @PostMapping("/product/add")
    public String addCategory(@ModelAttribute Category category){
        categoryService.addCategory(category);
        return "redirect:/manager/";
    }

    @GetMapping("/tree/add")
    public String addCategoryTree(Model model){
        model.addAttribute("categoryTree", new CategoryTree());
        return "back-end/add-new-category-tree";
    }
    @PostMapping("/tree/add")
    public String addCategoryTree(@ModelAttribute CategoryTree categoryTree){
        categoryTreeService.addCategory(categoryTree);
        return "redirect:/manager/";
    }

    @GetMapping("/product/all")
    public String getAllCategory(Model model){
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        return "back-end/category";
    }
    @GetMapping("/tree/all")
    public String getAllCategoryTree(Model model){
        List<CategoryTree> categoryTrees = categoryTreeService.getAllCategory();
        model.addAttribute("categoryTrees", categoryTrees);
        return "back-end/category-tree";
    }

    @DeleteMapping("/product/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long id){
        List<Product> products = productRepository.findByCategory_id(id);
        if(products.isEmpty()){
            categoryService.deleteCategory(id);
        }else {
            productRepository.deleteAll(products);
            categoryService.deleteCategory(id);
        }
        return ResponseEntity.ok("Sản phẩm đã được xóa thành công");
    }


}
