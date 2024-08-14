package com.ngfrt.appmain.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ngfrt.appmain.model.dto.DateDTO;
import com.ngfrt.appmain.model.dto.EventDTO;
import com.ngfrt.appmain.model.dto.EventInfoDTO;
import com.ngfrt.appmain.model.dto.TicketDTO;
import com.ngfrt.appmain.model.mapper.EventMapper;
import com.ngfrt.appmain.service.exception.EventNotFoundException;
import com.ngfrt.appmain.service.exception.EventServiceException;
import com.ngfrt.appmain.util.email.MailSender;
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

@Service
public class EventService {

    private final HallService hallService;
    private RestTemplate restTemplate;
    private final String eventServiceUrl;
    private final EventMapper eventMapper;
    private final MailSender mailSender;

    public EventService(RestTemplate restTemplate,
                        @Value("${api.event.service.url}") String eventServiceUrl, EventMapper eventMapper, HallService hallService, MailSender mailSender) {
        this.restTemplate = restTemplate;
        this.eventServiceUrl = eventServiceUrl;
        this.eventMapper = eventMapper;
        this.hallService = hallService;
        this.mailSender = mailSender;
    }

    public List<EventDTO> getAllEvents() {
        String response = restTemplate.getForObject(eventServiceUrl, String.class);

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        return gson.fromJson(response, new TypeToken<List<EventDTO>>(){}.getType());
    }

    public EventInfoDTO getEventByUuidString(String uuidString) {

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


    public String createNewEvent(EventDTO event) {
        HttpEntity<EventDTO> request = new HttpEntity<>(event);
        ResponseEntity<EventDTO> response = restTemplate.exchange(eventServiceUrl, HttpMethod.POST, request, EventDTO.class);

        //TODO - throw a custom exception when an event is not found - catch it with controller advice
        // and display an error to the user that event with such id doesn't exists
        if (!response.getStatusCode().is2xxSuccessful() || response.getHeaders().getLocation() == null || response.getBody() == null) {
            throw new EventServiceException("Failed event operation", response.getStatusCode().value());
        }
        sendNewEventEmail(response.getBody());

        return response.getHeaders().getLocation().toString();
    }

    private boolean sendNewEventEmail(EventDTO event) {
        //TODO - Get the user email address from the user uuid and send email to it.
        return false;
    }

    private String emailBuilder(EventDTO event) {
        //TODO get the hall name from the uuid and send the hall name instead of ID as it is now
        return String.format("Your Event:\n" +
                        "Event name: %s\n" +
                        "Date: %s\n" +
                        "Hall: %s\n" +
                        "Event Code (Supply this code to the attendees if your event is not public, or call the below number to make a group ticket booking): \n%s\n" +
                        "Group tickets number: +0700091280\n\n\n" +
                        "Contact our Logistics team at +0700090080 if you need to bring in large decor/exhibition pieces\n" +
                        "In case of any issues you can contact us at support@cartlandcc.support.com or +0700091080\n\n\n" +
                        "Best regards,\n" +
                        "The Cartland Convention Center",
                event.getName(), event.getDate().toString(), event.getHallId(), event.getUuid());
    }

}
