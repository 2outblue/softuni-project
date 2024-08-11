package com.ngfrt.appmain.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ngfrt.appmain.model.dto.EventDTO;
import com.ngfrt.appmain.util.gson.LocalDateAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    private RestTemplate restTemplate;
    private final String eventServiceUrl;

    public EventService(RestTemplate restTemplate,
                        @Value("${api.event.service.url}") String eventServiceUrl) {
        this.restTemplate = restTemplate;
        this.eventServiceUrl = eventServiceUrl;
    }

    public List<EventDTO> getAllEvents() {
        String response = restTemplate.getForObject(eventServiceUrl, String.class);

        //TODO  - this should be done by a service and the gson in the configuration,

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        return gson.fromJson(response, new TypeToken<List<EventDTO>>(){}.getType());
    }

    public void createNewEvent(EventDTO event) {
        restTemplate.postForLocation(eventServiceUrl, event);
    }
}
