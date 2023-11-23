package com.example.web_ban_cong.controller;

import com.example.web_ban_cong.model.Tree;
import com.example.web_ban_cong.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tree")
public class TreeController {
    @Autowired
    private TreeService treeService;
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
}
