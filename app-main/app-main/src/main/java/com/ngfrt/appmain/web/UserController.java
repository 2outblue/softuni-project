package com.ngfrt.appmain.web;

import com.ngfrt.appmain.model.dto.EventEditDTO;
import com.ngfrt.appmain.model.dto.UserAccountDTO;
import com.ngfrt.appmain.service.EventService;
import com.ngfrt.appmain.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/account")
public class UserController {

    private final UserService userService;
    private final EventService eventService;

    public UserController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
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

        if (!model.containsAttribute("userDTO")) {
            UserAccountDTO userAccountDTO = userService.getUserAccountInfo(principal.getUsername());
            model.addAttribute("userDTO", userAccountDTO);
        }
        return new ModelAndView("edit-account");
    }

    @PostMapping("/edit")
    public ModelAndView editUserAccount(@Valid UserAccountDTO userAccountDTO,
                                        BindingResult bindingResult,
                                        RedirectAttributes rAtt,
                                        @AuthenticationPrincipal User principal,
                                        Model model,
                                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("userDTO", userAccountDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userDTO", bindingResult);
            return new ModelAndView("redirect:/account/edit");
        }

        boolean emailChanged = userService.editUser(userAccountDTO, principal.getUsername(), request);
        if (emailChanged) {
            request.getSession().invalidate();
            return new ModelAndView("redirect:/");
        }

        UserAccountDTO newUserAccountInfoDTO = userService.getUserAccountInfo(userAccountDTO.getEmail());
        model.addAttribute("userDTO", newUserAccountInfoDTO);

        return new ModelAndView("user-panel");
    }

    @GetMapping("/bookings")
    public ModelAndView getUserEvents(@AuthenticationPrincipal User principal,
                                      Model model) {
        List<EventEditDTO> eventDTOs = eventService.getAllUserEvents(userService.getUserUuidByEmail(principal.getUsername()));
        int listSize = 0;
        if (!eventDTOs.isEmpty()) {
            listSize = eventDTOs.size();
        }
        model.addAttribute("bookingsSize", listSize);
        model.addAttribute("bookings", eventDTOs);

        return new ModelAndView("my-bookings");
    }
}
