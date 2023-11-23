package com.example.web_ban_cong.controller;

import com.example.web_ban_cong.service.ComboService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/combo")
public class ComboController {
    @Autowired
    private ComboService comboService;
    @Autowired
    @GetMapping("/detail/{comboId}")
    public String detailCombo(@PathVariable(name = "comboId")Long id, Model model){
        return "";
    }
}
