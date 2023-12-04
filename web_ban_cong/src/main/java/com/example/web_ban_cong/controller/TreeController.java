package com.example.web_ban_cong.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.web_ban_cong.model.*;
import com.example.web_ban_cong.repository.TreeImageRepository;
import com.example.web_ban_cong.service.CategoryTreeService;
import com.example.web_ban_cong.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @GetMapping("/all")
    public String allTree(Model model){
        List<Tree> trees =  treeService.getAllTree();
        model.addAttribute("trees", trees);
        return "front-end/shop-left-sidebar";
    }

    @GetMapping("/detail/{treeID}")
    public String treeDetail(@PathVariable(name = "treeID") Long id, Model model){
        Tree tree = treeService.getTreeById(id);
        System.out.println(tree.getHowToCare());
        if(tree != null){
            model.addAttribute("tree", tree);
            return "front-end/product-left-thumbnail.html";
        }else {
            return "front-end/404";
        }
    }

    @GetMapping("/add")
    public String addTree(Model model){
        List<CategoryTree> categoryTrees = categoryTreeService.getAllCategory();
        model.addAttribute("category", categoryTrees);
        model.addAttribute("tree", new Tree());
        return "back-end/add-new-tree";

    }

    @PostMapping("/add")
    public String addTree(@ModelAttribute Tree tree, @RequestParam("stateProduct") Long id
            , @RequestPart("file") MultipartFile file, @RequestParam("imageOther") MultipartFile[] images) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = (String) uploadResult.get("secure_url");
        CategoryTree categoryTree = categoryTreeService.getCategoryById(id);
        tree.setImage(imageUrl);
        tree.setCategory_tree(categoryTree);
        System.out.println(tree.getName());
        treeService.addTree(tree);
        for (MultipartFile image : images) {
            Map<?, ?> uploadResult1 = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());

            // Lấy URL của file đã tải lên thành công
            String imageUrlOther = (String) uploadResult1.get("secure_url");
            treeImageRepository.save(TreeImage.builder().flowers_and_trees(tree).imageUrl(imageUrlOther).build());
        }
        return "redirect:/tree/add";
    }
}
