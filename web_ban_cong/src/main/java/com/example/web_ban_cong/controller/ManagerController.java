package com.example.web_ban_cong.controller;

import com.example.web_ban_cong.model.Product;
import com.example.web_ban_cong.model.ProductImage;
import com.example.web_ban_cong.model.Tree;
import com.example.web_ban_cong.service.ProductService;
import com.example.web_ban_cong.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ProductService productService;
    @Autowired
    private TreeService treeService;

    @GetMapping("/")
    public String index(){
        return "back-end/index";
    }
    @GetMapping("/all")
    public String getAllProduct(Model model){
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "back-end/products";
    }

    @GetMapping("/tree/all")
    public String getAllTree(Model model){
        List<Tree> trees = treeService.getAllTree();
        model.addAttribute("trees", trees);
        return "back-end/trees";
    }
}
