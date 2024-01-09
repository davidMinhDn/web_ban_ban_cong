package com.example.web_ban_cong.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.web_ban_cong.model.*;
import com.example.web_ban_cong.repository.TreeImageRepository;
import com.example.web_ban_cong.repository.WishlistTreeRepository;
import com.example.web_ban_cong.service.CategoryTreeService;
import com.example.web_ban_cong.service.TreeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tree")
public class TreeController {
    @Autowired
    private TreeService treeService;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private CategoryTreeService categoryTreeService;
    @Autowired
    private TreeImageRepository treeImageRepository;
    @Autowired
    private WishlistTreeRepository wishlistTreeRepository;
    @GetMapping("/all")
    public String allTree(Model model){
        List<Tree> trees =  treeService.getAllTree();
        model.addAttribute("trees", trees);
        return "front-end/shop-left-sidebar";
    }

    @GetMapping("/detail/{treeID}")
    public String treeDetail(@PathVariable(name = "treeID") Long id, Model model){
        Tree tree = treeService.getTreeById(id);
        List<TreeImage> treeImages = treeImageRepository.findByTreeId(id);
        List<Tree> treeRelated  =  treeService.getTreeByCategory(tree.getCategoryTree().getId());
        treeRelated.remove(tree);
        List<Tree> treeNotRelated = treeService.getAllTree();
        for (Tree tr:
             treeRelated) {
            treeNotRelated.remove(tr);
        }
        model.addAttribute("treeNotRelated", treeNotRelated);
        model.addAttribute("treeRelated", treeRelated);
        model.addAttribute("treeImages", treeImages);
        model.addAttribute("tree", tree);
        return "front-end/tree-detail";
    }

    @GetMapping("/add")
    public String addTree(Model model){
        List<CategoryTree> categoryTrees = categoryTreeService.getAllCategory();
        model.addAttribute("category", categoryTrees);
        model.addAttribute("tree", new Tree());
        return "back-end/add-new-tree";

    }
    @GetMapping("/category/{categoryId}")
    public String treeByCategory(@PathVariable("categoryId") Long id, Model model, HttpSession session){
        List<Tree> wishlistTr = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        if(user != null) {
            List<WishlistTree> wishlistTrees = wishlistTreeRepository.findByUserId(user.getId());
            for (WishlistTree wishlistTree:
                    wishlistTrees) {
                wishlistTr.add(wishlistTree.getTree());
            }
        }
        List<CategoryTree> categoryTrees = categoryTreeService.getAllCategory();
        List<Tree> trees;
        if(id == 0){
            trees = treeService.getAllTree();
        }else {
            trees = treeService.getTreeByCategory(id);
        }
        model.addAttribute("wishlistTrees", wishlistTr);
        model.addAttribute("categoryId", id);
        model.addAttribute("categoryTrees", categoryTrees);
        model.addAttribute("trees", trees);
        return "front-end/shop-category-tree";
    }

    @PostMapping("/add")
    public String addTree(@ModelAttribute Tree tree, @RequestParam("stateProduct") Long id
            , @RequestPart("file") MultipartFile file, @RequestParam("imageOther") MultipartFile[] images) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = (String) uploadResult.get("secure_url");
        CategoryTree categoryTree = categoryTreeService.getCategoryById(id);
        tree.setImage(imageUrl);
        tree.setCategoryTree(categoryTree);
        System.out.println(tree.getName());
        treeService.addTree(tree);
        for (MultipartFile image : images) {
            Map<?, ?> uploadResult1 = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());

            // Lấy URL của file đã tải lên thành công
            String imageUrlOther = (String) uploadResult1.get("secure_url");
            treeImageRepository.save(TreeImage.builder().tree(tree).imageUrl(imageUrlOther).build());
        }
        return "redirect:/tree/add";
    }
}
