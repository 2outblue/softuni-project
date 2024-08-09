package com.ngfrt.appmain.service;

import com.ngfrt.appmain.config.SecurityConfiguration;
import com.ngfrt.appmain.util.calendar.Day;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarService {


    private final SecurityConfiguration securityConfiguration;

    public CalendarService(SecurityConfiguration securityConfiguration) {
        this.securityConfiguration = securityConfiguration;
    }

    public List<List<Day>> getWeeksForMonth(YearMonth yearMonth) {
        // instead of having a Week class just for that
        List<List<Day>> weeks = new ArrayList<>();
        List<Day> week = new ArrayList<>();

        // first and last days of the month
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

        // Adds a leading week row if the first day is not sunday. Explanation:
        // If the first day of the month doesn't start at sunday == problem - the user will be
        // able to select days starting from the first sunday only (which may be the 7th day of
        //  the month... and the user wont be able to select days 1 - 6), so - generate the days
        // of the previous month to fill the table cells remaining to the first day of
        // the input month and generate the first day/s on the input month to the first sunday...
        if (firstDayOfMonth.getDayOfWeek().getValue() != 7) {
            int dayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();
            int difference = 7 - dayOfWeek;

            LocalDate previousMonth = firstDayOfMonth.minusDays(difference);
            YearMonth ym = YearMonth.from(previousMonth);
            int previousMonthLength = ym.lengthOfMonth();

            int previousMonthRemainingDays = 7 - difference;
            // Generate Days of the previous month
            for (int i = 1; i <= previousMonthRemainingDays ; i++) {
                int dayOfPreviousMonth = previousMonthLength - (previousMonthRemainingDays - i);
                week.add(new Day(dayOfPreviousMonth, true));
            }

            // Generate Days of the input month
            for (int i = 1; i <= difference ; i++) {
                week.add(new Day(i, false));
            }
            weeks.add(week);
            week = new ArrayList<>();
        }
        // Now we start from the first sunday of the input month (second row if the month doesn't start with a sunday)
        LocalDate current = firstDayOfMonth.with(java.time.DayOfWeek.SUNDAY);

        // go through all days of the month
        while (!current.isAfter(lastDayOfMonth)) {
            // add the day to the week
            // TODO: implement filtering logic for event info
            week.add(new Day(current.getDayOfMonth(), false));

            // start a new week on saturday
            if (current.getDayOfWeek() == java.time.DayOfWeek.SATURDAY) {
                weeks.add(week);
                week = new ArrayList<>();
            }

            current = current.plusDays(1);
        }

        // add the last week only if it contains days
        if (!week.isEmpty()) {
            weeks.add(week);
        }

        return weeks;
    }
}
