package com.example.web_ban_cong.controller;

import com.example.web_ban_cong.model.Category;
import com.example.web_ban_cong.model.CategoryTree;
import com.example.web_ban_cong.model.Combo;
import com.example.web_ban_cong.model.Product;
import com.example.web_ban_cong.repository.ComboImageRepository;
import com.example.web_ban_cong.repository.ComboRepository;
import com.example.web_ban_cong.service.CategoryService;
import com.example.web_ban_cong.service.CategoryTreeService;
import com.example.web_ban_cong.service.ComboService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private ComboRepository comboRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryTreeService categoryTreeService;
    @GetMapping("/login")
    public String login (){
        return "front-end/login";
    }
    @GetMapping("/")
    public String index(Model model, HttpSession session){
        List<Combo> combosSale1 = comboRepository.findByPageSale(1);
        List<Combo> combosSale2 = comboRepository.findByPageSale(2);
        List<Combo> combosSale3 = comboRepository.findByPageSale(3);
        List<Category> categories = categoryService.getAllCategory();
        List<CategoryTree> categoryTrees = categoryTreeService.getAllCategory();
        session.setAttribute("categories", categories );
        session.setAttribute("categoryTrees", categoryTrees);
        model.addAttribute("comboSale1", combosSale1);
        model.addAttribute("comboSale2", combosSale2);
        model.addAttribute("comboSale3", combosSale3);
        return "front-end/index";
    }

    @GetMapping("/error")
    public String error(){
        return "front-end/404";
    }

}
