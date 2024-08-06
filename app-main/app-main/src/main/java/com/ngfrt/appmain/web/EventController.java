package com.ngfrt.appmain.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ngfrt.appmain.model.dto.EventDTO;
import com.ngfrt.appmain.util.gson.LocalDateAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EventController {


    private final RestTemplate restTemplate;

    public EventController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/api/events")
    public ResponseEntity<List<EventDTO>> getEvents(@Value("${api.event.service.url}") String eventServiceUrl) {

        String response = restTemplate.getForObject(eventServiceUrl, String.class);

        //TODO  - this should be done by a service and the gson in the configuration

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        List<EventDTO> events = gson.fromJson(response, new TypeToken<List<EventDTO>>(){}.getType());
        return ResponseEntity.ok(events);
    }
}
