package com.example.web_ban_cong.controller;

import com.example.web_ban_cong.OTP.MailSending;
import com.example.web_ban_cong.dto.UserDTO;
import com.example.web_ban_cong.model.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.FieldError;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/register")
    public String createUser(Model model){
        model.addAttribute("userDTO", new User());
        return "front-end/sign-up";
    }
    @PostMapping("/register")
    public String createUser(@Valid @ModelAttribute User user, BindingResult result, Model model, HttpSession session){
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            model.addAttribute("errors", errorMessages);
            model.addAttribute("userDTO", user);
            return "front-end/sign-up";
        }
        else {
            session.setAttribute("user", user);
            return "redirect:/user/re-sentOtp";
        }
    }
    @GetMapping("/re-sentOtp")
    public String resentOtp(HttpSession session ){
        User user = (User) session.getAttribute("user");
        sendOtp(session, user);
        return "front-end/otp.html";
    }
    public void sendOtp(HttpSession session, User user){
        MailSending mail = new MailSending();
        String opt = mail.generateOtp();
        session.setAttribute("optValue", opt);
        Thread thread = new Thread() {
            @Override
            public void run() {
                mail.authenEmail("doanngocminh15112002gl@gmail.com", "pwps rcdu tpmx ngon", user.getMail(), opt);
            }
        };
        thread.start();
    }


}
