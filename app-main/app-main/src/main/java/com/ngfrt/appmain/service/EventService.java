package com.ngfrt.appmain.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ngfrt.appmain.model.dto.DateDTO;
import com.ngfrt.appmain.model.dto.EventDTO;
import com.ngfrt.appmain.service.exception.EventServiceException;
import com.ngfrt.appmain.util.gson.LocalDateAdapter;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
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

    public EventDTO mapDate(EventDTO eventDTO, DateDTO dateDTO){
        return eventDTO.setDate(LocalDate.of(dateDTO.getYear(), dateDTO.getMonthValue(), dateDTO.getDayOfMonth()));
    }


    public void createNewEvent(EventDTO event) {
        HttpEntity<EventDTO> request = new HttpEntity<>(event);
        ResponseEntity<String> response = restTemplate.exchange(eventServiceUrl, HttpMethod.POST, request, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new EventServiceException("Failed event operation", response.getStatusCode().value());
        }
    }
}
