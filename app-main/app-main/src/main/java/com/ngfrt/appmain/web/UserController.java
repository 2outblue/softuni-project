package com.ngfrt.appmain.web;

import com.ngfrt.appmain.model.dto.UserAccountDTO;
import com.ngfrt.appmain.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/account")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/panel")
    public ModelAndView getUserPanel(@AuthenticationPrincipal User principal,
                                     Model model) {
        UserAccountDTO userAccountDTO = userService.getUserAccountInfo(principal.getUsername());
        model.addAttribute("userDTO", userAccountDTO);
        return new ModelAndView("user-panel");
    }

    @GetMapping("/edit")
    public ModelAndView getEditUserAccountForm(@AuthenticationPrincipal User principal,
                                        Model model) {

        UserAccountDTO userAccountDTO = userService.getUserAccountInfo(principal.getUsername());
        model.addAttribute("userDTO", userAccountDTO);

        return new ModelAndView("edit-account");
    }

    @PostMapping("/edit")
    public ModelAndView editUserAccount(HttpServletRequest request,
                                        @ModelAttribute("userDTO") UserAccountDTO userAccountDTO,
                                        @AuthenticationPrincipal User principal,
                                        Model model) {
        boolean emailChanged = userService.editUser(userAccountDTO, principal.getUsername(), request);
        if (emailChanged) {
            request.getSession().invalidate();
            return new ModelAndView("redirect:/");
        }

        UserAccountDTO newUserAccountInfoDTO = userService.getUserAccountInfo(userAccountDTO.getEmail());
        model.addAttribute("userDTO", newUserAccountInfoDTO);

        return new ModelAndView("user-panel");
    }
}
