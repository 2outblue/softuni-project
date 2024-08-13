package com.ngfrt.appmain.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ngfrt.appmain.model.dto.DateDTO;
import com.ngfrt.appmain.model.dto.EventDTO;
import com.ngfrt.appmain.model.dto.EventInfoDTO;
import com.ngfrt.appmain.model.mapper.EventMapper;
import com.ngfrt.appmain.service.exception.EventNotFoundException;
import com.ngfrt.appmain.service.exception.EventServiceException;
import com.ngfrt.appmain.util.gson.LocalDateAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EventService {

    private final HallService hallService;
    private RestTemplate restTemplate;
    private final String eventServiceUrl;
    private final EventMapper eventMapper;

    public EventService(RestTemplate restTemplate,
                        @Value("${api.event.service.url}") String eventServiceUrl, EventMapper eventMapper, HallService hallService) {
        this.restTemplate = restTemplate;
        this.eventServiceUrl = eventServiceUrl;
        this.eventMapper = eventMapper;
        this.hallService = hallService;
    }

    public List<EventDTO> getAllEvents() {
        String response = restTemplate.getForObject(eventServiceUrl, String.class);

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        return gson.fromJson(response, new TypeToken<List<EventDTO>>(){}.getType());
    }

    public EventInfoDTO getEventByUuid(String uuidString) {

        try {
            UUID.fromString(uuidString);
        } catch (IllegalArgumentException ex) {
            throw new EventNotFoundException("Event with code " + uuidString + " not found. It looks like this is an invalid Event code, please make sure you are providing a correct Event code");
        }
        UUID uuid = UUID.fromString(uuidString);
        HttpEntity<UUID> request = new HttpEntity<>(uuid);

        String url = eventServiceUrl + "/" + uuid;
        try {
            ResponseEntity<EventDTO> response = restTemplate.exchange(url, HttpMethod.GET, request, EventDTO.class);
            return eventMapper.toEventInfoDTO(response.getBody(), hallService);

        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new EventNotFoundException("Event with code " + uuid + " not found.");
            } else {
                throw new EventServiceException("Event operation failed", ex.getStatusCode().value());
            }
        }
    }

    public List<EventInfoDTO> getFeaturedEvents() {

        String url = eventServiceUrl + "/featured";
        String response = restTemplate.getForObject(url, String.class);

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        return gson.fromJson(response, new TypeToken<List<EventInfoDTO>>(){}.getType());
    }


    public EventDTO mapDate(EventDTO eventDTO, DateDTO dateDTO){
        return eventDTO.setDate(LocalDate.of(dateDTO.getYear(), dateDTO.getMonthValue(), dateDTO.getDayOfMonth()));
    }


    public void createNewEvent(EventDTO event) {
        HttpEntity<EventDTO> request = new HttpEntity<>(event);
        ResponseEntity<String> response = restTemplate.exchange(eventServiceUrl, HttpMethod.POST, request, String.class);

        //TODO - throw a custom exception when an event is not found - catch it with controller advice
        // and display an error to the user that event with such id doesn't exists
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new EventServiceException("Failed event operation", response.getStatusCode().value());
        }
    }

}
