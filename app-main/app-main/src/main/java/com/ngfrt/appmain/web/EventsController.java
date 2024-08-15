package com.ngfrt.appmain.web;

import com.ngfrt.appmain.model.dto.EventInfoDTO;
import com.ngfrt.appmain.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventsController {


    private final EventService eventService;

    public EventsController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/featured")
    public ModelAndView getFeaturedEvents(Model model) {
        List<EventInfoDTO> featuredEvents = eventService.getFeaturedEvents();
        model.addAttribute("events", featuredEvents);
        return new ModelAndView("featured-events");
    }

}
