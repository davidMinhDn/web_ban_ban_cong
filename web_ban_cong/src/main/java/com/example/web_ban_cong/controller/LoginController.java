package com.example.web_ban_cong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login (){
        return "front-end/login";
    }
    @GetMapping("/")
    public String index(){
        return "front-end/index";
    }

    @GetMapping("/error")
    public String error(){
        return "front-end/404";
    }

}
