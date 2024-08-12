package com.ngfrt.appmain.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/events")
public class EventsController {


    @GetMapping("/featured")
    public ModelAndView getFeaturedEvents() {


        return new ModelAndView("featured-events");
    }
}
