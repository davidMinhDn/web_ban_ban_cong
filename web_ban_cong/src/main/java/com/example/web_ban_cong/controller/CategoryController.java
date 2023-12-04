package com.example.web_ban_cong.controller;

import com.example.web_ban_cong.model.Category;
import com.example.web_ban_cong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/add")
    public String addCategory(Model model){
        model.addAttribute("category", new Category());
        return "back-end/add-new-category";
    }
    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category){
        System.out.println(category.getCategoryName());
        categoryService.addCategory(category);
        return "redirect:/manager/";
    }

}
