package com.example.web_ban_cong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/login")
    public String login(){
        return "front-end/login";
    }
}
