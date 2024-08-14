package com.ngfrt.appmain.web;

import com.ngfrt.appmain.model.dto.UserRegisterDTO;
import com.ngfrt.appmain.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserRegisterController {

    private final UserService userService;

    public UserRegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView registerForm(Model model) {
        if (!model.containsAttribute("userRegisterDTO")) {
            model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        }

        ModelAndView mav = new ModelAndView();
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(UserRegisterDTO userRegisterDTO) {
        userService.registerUser(userRegisterDTO);

        return new ModelAndView("redirect:/");
    }
}
