package com.ngfrt.appmain.web;

import com.ngfrt.appmain.model.dto.*;
import com.ngfrt.appmain.service.EventService;
import com.ngfrt.appmain.service.HallService;
import com.ngfrt.appmain.service.exception.EventNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/event")
public class EventController {


    private final HallService hallService;
    private final EventService eventService;


    public EventController(HallService hallService, EventService eventService) {
        this.hallService = hallService;
        this.eventService = eventService;
    }

    // Maybe move this elsewhere?
    @GetMapping("/api/events")
    public ResponseEntity<List<EventDTO>> getEvents() {

        List<EventDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/attend")
    public ModelAndView attend() {

        return new ModelAndView("attend");
    }

    @GetMapping("/search")
    public ModelAndView searchEvent(@RequestParam(required = false) String eventCode, Model model) {

        EventInfoDTO eventInfo = eventService.getEventByUuid(eventCode);
        model.addAttribute("event", eventInfo);

        return new ModelAndView("event-details");
    }

    @GetMapping("/purchase")
    public ModelAndView buyTicket(@RequestParam(required = false) String eventCode, Model model) {

        EventInfoDTO eventInfo = eventService.getEventByUuid(eventCode);
        if (eventInfo.isSoldOut()) {
            throw new EventNotFoundException("This event is sold out!");
        }
        model.addAttribute("ticketDTO", new TicketDTO());
        model.addAttribute("event", eventInfo);

        return new ModelAndView("buy-ticket");
    }

}
