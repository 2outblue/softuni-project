package com.ngfrt.appmain.web;

import com.ngfrt.appmain.model.dto.TicketDTO;
import com.ngfrt.appmain.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/purchase")
    public ModelAndView buyTicket(TicketDTO ticketDTO,
                                  @RequestParam UUID eventUuid,
                                  Model model) {

        ticketService.saveTicketAndSendEmail(ticketDTO, eventUuid);
        model.addAttribute("message", "Ticket successfully purchased. Please check your email for details!");

        return new ModelAndView("task-info");
    }
}
