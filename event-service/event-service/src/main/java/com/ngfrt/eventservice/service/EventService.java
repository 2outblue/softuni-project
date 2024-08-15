package com.ngfrt.eventservice.service;


import com.ngfrt.eventservice.model.dto.EventDTO;
import com.ngfrt.eventservice.model.dto.UpdateEventDTO;
import com.ngfrt.eventservice.model.entity.Event;
import com.ngfrt.eventservice.model.mapper.EventMapper;
import com.ngfrt.eventservice.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {



    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public List<EventDTO> getAllEvents() {
        List<Event> eventEntities = eventRepository.findAll();

        return mapToDTOs(eventEntities);
    }

    public Optional<EventDTO> getEventByUuid(UUID uuid) {
        Optional<Event> eventEntity = eventRepository.findByUuid(uuid);

        if (eventEntity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(eventMapper.toDto(eventEntity.get()));
    }

    @Transactional
    public void deleteEventByUuid(UUID uuid) {
        eventRepository.deleteByUuid(uuid);
    }

    public UUID addEvent(EventDTO eventDTO) {
        Event event = eventMapper.toEntity(eventDTO);
        event.setUuid(UUID.randomUUID());

        eventRepository.save(event);
        return event.getUuid();
    }

    public EventDTO updateEvent(UpdateEventDTO updateEventDTO, UUID uuid) {
        Optional<Event> eventEntity = eventRepository.findByUuid(uuid);
        if (eventEntity.isEmpty()) {
            return null;
        }

        Event updatedEvent = eventMapper.updateEntityFromDTO(eventEntity.get(), updateEventDTO);
        eventRepository.save(updatedEvent);

        Optional<Event> eventToReturn = eventRepository.findByUuid(updatedEvent.getUuid());
        return eventToReturn.map(eventMapper::toDto).orElse(null);
    }


    public List<EventDTO> getEventsByUserUuid(UUID userUuid) {
        List<Event> eventEntities = eventRepository.findAllByUserId(userUuid);

        return mapToDTOs(eventEntities);
    }

    public List<EventDTO> getEventsByHallUuid(UUID hallUuid) {
        List<Event> eventEntitiesOpt = eventRepository.findAllByHallId(hallUuid);

        return mapToDTOs(eventEntitiesOpt);
    }

    public List<EventDTO> getFeaturedEvents() {
        List<Event> eventEntities = eventRepository.findAllByFeatured(true);

        return mapToDTOs(eventEntities);
    }

    public List<EventDTO> getByYearAndMonth(int year, int month) {
        List<Event> eventEntities = eventRepository.findAllByYearAndMonth(year, month);

        return mapToDTOs(eventEntities);
    }

    public List<EventDTO> getByMonthAndDay(int month, int day) {
        List<Event> eventEntities = eventRepository.findAllByMonthAndDay(month, day);
        return mapToDTOs(eventEntities);
    }

    public EventDTO buyTicket(UUID uuid) {
        Optional<Event> eventOpt = eventRepository.findByUuid(uuid);
        if (eventOpt.isPresent()) {
            Event entity = eventOpt.get();
            int tickets = entity.getTickets();
            entity.setTickets(tickets + 1);
            eventRepository.save(entity);
            return eventMapper.toDto(entity);
        }
        return null;
    }

    private List<EventDTO> mapToDTOs(List<Event> eventEntities){
        return eventEntities.stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }
}

