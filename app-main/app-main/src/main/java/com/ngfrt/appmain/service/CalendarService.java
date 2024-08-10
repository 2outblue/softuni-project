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

    public List<List<Day>> getWeeksForMonth(YearMonth yearMonthInput) {
        // instead of having a Week class just for that
        List<List<Day>> weeks = new ArrayList<>();
        List<Day> week = new ArrayList<>();

        // first and last days of the month
        LocalDate firstDayOfMonth = yearMonthInput.atDay(1);
        LocalDate lastDayOfMonth = yearMonthInput.atEndOfMonth();

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

                // Disable days if they are in the past
                boolean disabled = false;
                if (((i <= LocalDate.now().getDayOfMonth() && yearMonthInput.getMonthValue() <= LocalDate.now().getMonthValue())  ||
                        yearMonthInput.getMonthValue() < LocalDate.now().getMonthValue()) && yearMonthInput.getYear() <= LocalDate.now().getYear()) {
                    disabled = true;
                }
//                boolean disabled = i <= LocalDate.now().getDayOfMonth();
//                if (yearMonthInput.getMonthValue() < LocalDate.now().getMonthValue()) {
//                    disabled = true;
//                }
                week.add(new Day(i, disabled));
            }
            weeks.add(week);
            week = new ArrayList<>();
        }
        // Now we start from the first sunday of the input month (second row if the month doesn't start with a sunday)
        LocalDate current = firstDayOfMonth.with(java.time.DayOfWeek.SUNDAY);

        // go through all days of the month
        while (!current.isAfter(lastDayOfMonth)) {
            // TODO: implement filtering logic for event info

            // Disable days if they are in the past
            boolean disabled = false;
            if (((current.getDayOfMonth() <= LocalDate.now().getDayOfMonth() && yearMonthInput.getMonthValue() <= LocalDate.now().getMonthValue())  ||
                    yearMonthInput.getMonthValue() < LocalDate.now().getMonthValue()) && yearMonthInput.getYear() <= LocalDate.now().getYear()) {
                disabled = true;
            }
            // add the day to the week
            week.add(new Day(current.getDayOfMonth(), disabled));

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
