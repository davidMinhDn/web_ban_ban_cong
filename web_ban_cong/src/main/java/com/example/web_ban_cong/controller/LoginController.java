package com.example.web_ban_cong.controller;

import com.example.web_ban_cong.model.*;
import com.example.web_ban_cong.repository.ComboImageRepository;
import com.example.web_ban_cong.repository.ComboRepository;
import com.example.web_ban_cong.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private ComboRepository comboRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryTreeService categoryTreeService;
    @Autowired
    private UserService userService;
    @Autowired
    private ManagerService managerService;
    @PostMapping("/login")
    public String login (@RequestPart("email") String email, @RequestPart("password") String pass,
                         HttpSession session){
        System.out.println(email);
        System.out.println(pass);
        User user = userService.loginByMailAndPass(email, pass);
        if (user == null){
            Manager manager = managerService.loginByMailAndPass(email, pass);
            if(manager != null){
                session.setAttribute("manager", manager);
                return "redirect:/manager/";
            }else {
                return "front-end/404";
            }
        }else {
            session.setAttribute("user", user);
            return "redirect:/";
        }
    }
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
