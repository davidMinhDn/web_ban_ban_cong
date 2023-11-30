package com.example.web_ban_cong.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.web_ban_cong.model.Tree;
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
        model.addAttribute("tree", new Tree());
        return "back-end/add-new-tree";

    }

    @PostMapping("/add")
    public String addTree(@ModelAttribute Tree tree, @RequestPart("file") MultipartFile file) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = (String) uploadResult.get("secure_url");
        tree.setImage(imageUrl);
        treeService.addTree(tree);
        return "redirect:/tree/add";
    }
}
