package com.ngfrt.appmain.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserRegisterController {

    @GetMapping("/register")
    public ModelAndView registerForm() {

        return new ModelAndView("register");
    }
}
