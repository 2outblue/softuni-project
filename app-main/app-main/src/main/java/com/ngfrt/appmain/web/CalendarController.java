package com.ngfrt.appmain.web;

import com.ngfrt.appmain.service.CalendarService;
import com.ngfrt.appmain.util.calendar.Day;
import com.ngfrt.appmain.util.calendar.Month;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Controller
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/calendar")
    public String getMonthCalendar(@RequestParam(required = false) Integer year,
                                   @RequestParam(required = false) Integer month,
                                   Model model) {

        if (year == null) {
            year = Year.now().getValue();
        }
        if (month == null) {
            month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        }

        List<List<Day>> weeks = calendarService.getWeeksForMonth(YearMonth.of(year, month));
        model.addAttribute("weeks", weeks);
        model.addAttribute("months", Month.getMonths());
        return "calendar";


    }

}
