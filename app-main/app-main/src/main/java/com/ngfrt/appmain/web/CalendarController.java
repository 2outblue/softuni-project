package com.ngfrt.appmain.web;

import com.ngfrt.appmain.model.dto.EventDTO;
import com.ngfrt.appmain.service.CalendarService;
import com.ngfrt.appmain.service.HallService;
import com.ngfrt.appmain.util.calendar.Day;
import com.ngfrt.appmain.util.calendar.Month;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.Year;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.List;

@Controller
public class CalendarController {

    private final CalendarService calendarService;
    private final HallService hallService;

    public CalendarController(CalendarService calendarService, HallService hallService) {
        this.calendarService = calendarService;
        this.hallService = hallService;
    }

    @GetMapping("/calendar/book")
    public String getMonthCalendar(@RequestParam(required = false) Integer year,
                                   @RequestParam(required = false) Integer monthValue,
                                   EventDTO event,
                                   Model model) {

        if (year == null) {
            year = Year.now().getValue();
        }
        if (monthValue == null) {
            monthValue = Calendar.getInstance().get(Calendar.MONTH) + 1;
        }

        List<List<Day>> weeks = calendarService
                .getWeeksForMonth(YearMonth.of(year, monthValue), event.getHallId());
        model.addAttribute("monthNumber", monthValue);
        model.addAttribute("year", year);
        model.addAttribute("weeks", weeks);
        model.addAttribute("months", Month.getMonths());
        model.addAttribute("event", event);
        model.addAttribute("hallName", hallService.getHallNameByUuid(event.getHallId()));
        return "calendar";
    }

    @GetMapping("/calendar")
    public String chooseEventDate(EventDTO eventDTO,
                                  Model model) {

        List<List<Day>> weeks = calendarService
                .getWeeksForMonth(YearMonth.of(Year.now().getValue(), Calendar.getInstance().get(Calendar.MONTH) + 1),
                eventDTO.getHallId());

        model.addAttribute("weeks", weeks);
        model.addAttribute("months", Month.getMonths());
        model.addAttribute("event", eventDTO);
        return "calendar";
    }
}
