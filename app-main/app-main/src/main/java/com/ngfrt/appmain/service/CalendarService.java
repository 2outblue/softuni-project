package com.ngfrt.appmain.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ngfrt.appmain.config.SecurityConfiguration;
import com.ngfrt.appmain.model.dto.EventCalendarDTO;
import com.ngfrt.appmain.model.dto.EventDTO;
import com.ngfrt.appmain.model.dto.EventInfoDTO;
import com.ngfrt.appmain.model.mapper.EventMapper;
import com.ngfrt.appmain.util.calendar.Day;
import com.ngfrt.appmain.util.gson.LocalDateAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CalendarService {


    private final RestTemplate restTemplate;
    private final String eventServiceUrl;
    private final EventMapper eventMapper;

    public CalendarService(RestTemplate restTemplate,
                           @Value("${api.event.service.url}") String eventServiceUrl, EventMapper eventMapper) {
        this.restTemplate = restTemplate;
        this.eventServiceUrl = eventServiceUrl;
        this.eventMapper = eventMapper;
    }

    public List<List<Day>> getWeeksForMonth(YearMonth yearMonthInput, UUID eventHallUuid) {
        // instead of having a Week class just for that
        List<List<Day>> weeks = new ArrayList<>();
        List<Day> week = new ArrayList<>();
        List<EventCalendarDTO> eventsForSelectedMonth = getEventsForYearAndMonth(yearMonthInput.getYear(), yearMonthInput.getMonthValue());

        // first and last days of the month
        LocalDate firstDayOfMonth = yearMonthInput.atDay(1);
        LocalDate lastDayOfMonth = yearMonthInput.atEndOfMonth();

        // Adds a leading week row if the first day is not sunday. Explanation:
        // If the first day of the month doesn't start at sunday == problem - the user will be
        // able to select days starting from the first sunday only (which may be the 7th day of
        //  the month... and the user wont be able to select days 1 - 6), so - generate the days
        // of the previous month to fill the table cells remaining to the first day of
        // the input month and generate the first day/s on the input month to the first sunday..
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
                // Create a day and add it to the week
                week.add(createDay(eventsForSelectedMonth, i, disabled, eventHallUuid));
            }
            weeks.add(week);
            week = new ArrayList<>();
        }
        // Now we start from the first sunday of the input month (second row if the month doesn't start with a sunday)
        LocalDate current = firstDayOfMonth.with(java.time.DayOfWeek.SUNDAY);

        // go through all days of the month
        while (!current.isAfter(lastDayOfMonth)) {
            // Disable days if they are in the past
            boolean disabled = false;
            if (((current.getDayOfMonth() <= LocalDate.now().getDayOfMonth() && yearMonthInput.getMonthValue() <= LocalDate.now().getMonthValue())  ||
                    yearMonthInput.getMonthValue() < LocalDate.now().getMonthValue()) && yearMonthInput.getYear() <= LocalDate.now().getYear()) {
                disabled = true;
            }

            // Create a day and add it to the week
            week.add(createDay(eventsForSelectedMonth, current.getDayOfMonth(), disabled, eventHallUuid));

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


    private List<EventCalendarDTO> getEventsForYearAndMonth(int year, int month) {
        String url = String.format("%s/%d/%d",eventServiceUrl, year, month);

        String response = restTemplate.getForObject(url, String.class);
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        List<EventDTO> entities = gson.fromJson(response, new TypeToken<List<EventDTO>>() {}.getType());

        System.out.println();
        System.out.println();
        return entities != null ? entities.stream().map(eventMapper::toEventCalendarDTO).toList() : null;
    }

    // Creates a day and checks if any of the existing events for the selected month match the DATE and HALL of the provided
    //  day of the month (int currentDay) - if there is an event on this date in the same hall - this day is disabled and
    //  the name of the event is displayed in this cell.
    private Day createDay(List<EventCalendarDTO> events, int currentDay, boolean disabled, UUID eventHallUuid) {
        Day d = new Day(currentDay, disabled);
        if (events != null && !events.isEmpty()) {
            List<EventCalendarDTO> eventL = events.stream()
                    .filter(e -> e.getDayOfMonth() == currentDay)
                    .toList();
            if (!eventL.isEmpty()) {
                EventCalendarDTO eventCalendarDTO = eventL.getFirst();
                if (eventCalendarDTO.getHallId().equals(eventHallUuid)) {
                    d.setDisabled(true).setTextContent(eventL.getFirst().getName());
                }
            }
        }
        return d;
    }
}
