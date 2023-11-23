package com.example.web_ban_cong.controller;

import com.example.web_ban_cong.model.Combo;
import com.example.web_ban_cong.repository.ComboImageRepository;
import com.example.web_ban_cong.service.ComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private ComboService comboService;
    @Autowired
    private ComboImageRepository comboImageRepository;
    @GetMapping("/login")
    public String login (){
        return "front-end/login";
    }
    @GetMapping("/")
    public String index(Model model){
        List<Combo> combos = comboService.getAllCombo();
        model.addAttribute("combos", combos);
        return "front-end/index";
    }

    @GetMapping("/error")
    public String error(){
        return "front-end/404";
    }

}
