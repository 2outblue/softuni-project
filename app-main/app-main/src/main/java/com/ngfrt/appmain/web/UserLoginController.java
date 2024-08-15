package com.ngfrt.appmain.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserLoginController {

    @GetMapping("/login")
    public ModelAndView loginError(@RequestParam(value = "error", required = false) String error,
                                   Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid user credentials!");
        }
        return new ModelAndView("login");
    }
}
