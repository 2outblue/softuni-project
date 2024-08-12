package com.ngfrt.appmain.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserLoginController {

    @GetMapping("/login")
    public ModelAndView loginForm() {

        return new ModelAndView("login");
    }
}
