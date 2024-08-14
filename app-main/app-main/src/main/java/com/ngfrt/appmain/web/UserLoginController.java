package com.ngfrt.appmain.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserLoginController {

    @GetMapping("/login")
    public ModelAndView loginForm() {

        return new ModelAndView("login");
    }

    @PostMapping("/login-error")
    public ModelAndView loginError(@ModelAttribute String email, Model model) {
        model.addAttribute("invalidCredentials", true);
        return new ModelAndView("login");
    }
}
