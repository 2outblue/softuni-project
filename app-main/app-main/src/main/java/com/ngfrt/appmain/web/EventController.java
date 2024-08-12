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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


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
    public ModelAndView searchEvent(@RequestParam String eventCode, Model model) {



        return new ModelAndView("index");
    }



//    @GetMapping("/plan")
//    public ModelAndView planEvent() {
//
//        return new ModelAndView("event-plan");
//    }
//
//    @GetMapping("/form")
//    public ModelAndView eventForm(Model model) {
//        List<HallListingDTO> halls = hallService.getAllHallsForListing();
//        model.addAttribute("halls", halls);
//        model.addAttribute("eventDTO", new EventDTO());
//
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("book-event");
//        return mav;
//    }
//
//    @GetMapping("/finalize")
//    public ModelAndView finalizeBooking(@RequestParam String hallName,
//                                        EventDTO eventDTO,
//                                        DateDTO dateDTO,
//                                        Model model) {
//
//        EventDTO event = eventService.mapDate(eventDTO, dateDTO);
//
//        model.addAttribute("hallName", hallName);
//        model.addAttribute("event", eventDTO);
//        return new ModelAndView("booking-finalize");
//    }
//
//    @PostMapping("/create")
//    public ModelAndView createNewEvent(EventDTO eventDTO) {
//        eventService.createNewEvent(eventDTO);
//
//        return new ModelAndView("event-created");
//    }


}
