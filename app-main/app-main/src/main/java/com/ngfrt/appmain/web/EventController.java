package com.ngfrt.appmain.web;

import com.ngfrt.appmain.model.dto.DateDTO;
import com.ngfrt.appmain.model.dto.EventDTO;
import com.ngfrt.appmain.model.dto.HallListingDTO;
import com.ngfrt.appmain.service.EventService;
import com.ngfrt.appmain.service.HallService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

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
    public ModelAndView eventForm(Model model) {
        List<HallListingDTO> halls = hallService.getAllHallsForListing();
        model.addAttribute("halls", halls);
        model.addAttribute("eventDTO", new EventDTO());

        ModelAndView mav = new ModelAndView();
        mav.setViewName("book-event");
        return mav;
    }

    @GetMapping("/event/finalize")
    public ModelAndView finalizeBooking(@RequestParam String hallName,
                                        EventDTO eventDTO,
                                        DateDTO dateDTO,
                                        Model model) {

        EventDTO event = eventService.mapDate(eventDTO, dateDTO);

        model.addAttribute("hallName", hallName);
        model.addAttribute("event", eventDTO);
        return new ModelAndView("booking-finalize");
    }

    @PostMapping("/event/create")
    public ModelAndView createNewEvent(EventDTO eventDTO) {
        eventService.createNewEvent(eventDTO);

        return new ModelAndView("event-created");
    }


}
