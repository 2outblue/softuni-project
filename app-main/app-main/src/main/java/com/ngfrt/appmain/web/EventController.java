package com.ngfrt.appmain.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ngfrt.appmain.model.dto.EventDTO;
import com.ngfrt.appmain.model.dto.HallListingDTO;
import com.ngfrt.appmain.service.EventService;
import com.ngfrt.appmain.service.HallService;
import com.ngfrt.appmain.util.gson.LocalDateAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EventController {


    private final RestTemplate restTemplate;
    private final HallService hallService;
    private final EventService eventService;


    public EventController(RestTemplate restTemplate, HallService hallService, EventService eventService) {
        this.restTemplate = restTemplate;
        this.hallService = hallService;
        this.eventService = eventService;
    }

    // Maybe move this elsewhere?
    @GetMapping("/api/events")
    public ResponseEntity<List<EventDTO>> getEvents() {

        List<EventDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/event/form")
    public ModelAndView bookEvent(Model model) {
        List<HallListingDTO> halls = hallService.getAllHallsForListing();
        model.addAttribute("halls", halls);
        model.addAttribute("eventDTO", new EventDTO());

        ModelAndView mav = new ModelAndView();
        mav.setViewName("book-event");
        return mav;
    }

    @PostMapping("/event/book")
    public ModelAndView bookEvent(EventDTO eventDTO,
                                  RedirectAttributes rAtt) {

        rAtt.addFlashAttribute("eventDTO", eventDTO);
        return new ModelAndView("redirect:/event/book/date");
    }



}
