package com.ngfrt.appmain.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ngfrt.appmain.model.dto.EventDTO;
import com.ngfrt.appmain.util.gson.LocalDateAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

//Every day at midnight get events which are two days old and delete them..
@Component
public class EventCleanupTask {

    private final RestTemplate restTemplate;
    private final String eventServiceUrl;

    public EventCleanupTask(@Value("${api.event.service.url}") String eventServiceUrl) {
        this.eventServiceUrl = eventServiceUrl;
        this.restTemplate = new RestTemplate();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteOldEvents() {

        LocalDate twoDaysAgo = LocalDate.now().minusDays(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        int month = twoDaysAgo.getMonthValue();
        int day = twoDaysAgo.getDayOfMonth();
        String formattedDate = twoDaysAgo.format(formatter);
        System.out.println("Deleting events older than: " + formattedDate);

        List<EventDTO> eventsForDeletion = getEventsForYearAndMonth(month, day);

        if (eventsForDeletion == null || eventsForDeletion.isEmpty()) {
            System.out.println("Attempted deletion of two-day old events - but there were none");
            return;
        }

        for (EventDTO eventDTO : eventsForDeletion) {
            try {
                restTemplate.delete(eventServiceUrl + "/" + eventDTO.getUuid());
            } catch (HttpClientErrorException e) {
                System.out.println("Could not delete event!");
            }
        }
        System.out.println("Deleted events older than: " + formattedDate);
    }

    private List<EventDTO> getEventsForYearAndMonth(int month, int day) {
        String url = String.format("%s/monthday/%d/%d",eventServiceUrl, month, day);

        String response = restTemplate.getForObject(url, String.class);
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        return gson.fromJson(response, new TypeToken<List<EventDTO>>() {}.getType());
    }
}
