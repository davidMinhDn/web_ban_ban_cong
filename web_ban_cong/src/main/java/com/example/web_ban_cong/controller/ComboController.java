package com.example.web_ban_cong.controller;

import com.example.web_ban_cong.model.*;
import com.example.web_ban_cong.repository.ComboImageRepository;
import com.example.web_ban_cong.repository.ComboProductRepository;
import com.example.web_ban_cong.repository.ProductRepository;
import com.example.web_ban_cong.repository.TreeRepository;
import com.example.web_ban_cong.service.ComboService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/combo")
public class ComboController {
    @Autowired
    private ComboService comboService;
    @Autowired
    private ComboImageRepository comboImageRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TreeRepository treeRepository;
    @GetMapping("/detail/{comboId}")
    public String detailCombo(@PathVariable("comboId")Long id, Model model){
        List<Product> products = productRepository.findByCombos_Id(id);
        List<ComboImage> comboImages = comboImageRepository.findByComboId(id);
        Combo combo = comboService.getComboById(id);
        List<Tree> trees = treeRepository.findByCombos_Id(id);
        model.addAttribute("trees", trees);
        model.addAttribute("images", comboImages);
        model.addAttribute("products", products);
        model.addAttribute("combo", combo);
        return "front-end/product-slider";
    }
}
