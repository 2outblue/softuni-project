package com.ngfrt.appmain.web;

import com.ngfrt.appmain.model.dto.*;
import com.ngfrt.appmain.service.EventService;
import com.ngfrt.appmain.service.HallService;
import com.ngfrt.appmain.service.exception.EventNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView searchEvent(@RequestParam(required = false) String eventCode,
                                    Model model) {
        EventInfoDTO eventInfo = eventService.getEventInfoDTOByUuidString(eventCode);
        model.addAttribute("event", eventInfo);

        return new ModelAndView("event-details");
    }

    @GetMapping("/purchase")
    public ModelAndView buyTicketForm(@RequestParam(required = false) String eventCode, Model model) {

        EventInfoDTO eventInfo = eventService.getEventInfoDTOByUuidString(eventCode);
        if (eventInfo.isSoldOut()) {
            throw new EventNotFoundException("This event is sold out!");
        }

        TicketDTO ticketDTO = new TicketDTO()
                .setEventDate(eventInfo.getDate())
                .setEventName(eventInfo.getName())
                .setHallName(eventInfo.getHallName())
                .setHallUuid(eventInfo.getHallUuid());
        model.addAttribute("ticketDTO", ticketDTO);
        model.addAttribute("event", eventInfo);

        return new ModelAndView("buy-ticket");
    }

    @GetMapping("/edit")
    public ModelAndView editEvent(@RequestParam(value = "uuid", required = false) String uuid,
                                  Model model) {

        if (model.containsAttribute("eventDTO")) {
            return new ModelAndView("edit-booking");
        }
        EventEditDTO eventDTO = eventService.getEventEditDtoByUuidString(uuid);
        model.addAttribute("eventDTO", eventDTO);
        return new ModelAndView("edit-booking");
    }

    @PostMapping("/edit")
    public ModelAndView editEvent(@ModelAttribute("eventDTO") @Valid EventEditDTO eventDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes rAtt,
                                  Model model) {

        if (bindingResult.hasErrors()) {
            EventEditDTO oldDto = eventService.getEventEditDtoByUuidString(eventDTO.getUuid().toString());
            rAtt.addFlashAttribute("eventDTO", oldDto);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.eventDTO", bindingResult);
            return new ModelAndView("redirect:/event/edit");
        }

        eventService.updateEvent(eventDTO);
        EventEditDTO updatedEventDTO = eventService.getEventEditDtoByUuidString(eventDTO.getUuid().toString());

        model.addAttribute("eventDTO", updatedEventDTO);
        return new ModelAndView("edit-booking");
    }
}
