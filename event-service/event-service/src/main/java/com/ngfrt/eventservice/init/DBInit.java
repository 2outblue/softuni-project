package com.ngfrt.eventservice.init;

import com.ngfrt.eventservice.model.entity.Event;
import com.ngfrt.eventservice.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DBInit implements CommandLineRunner {

    private final EventRepository eventRepository;

    public DBInit(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (eventRepository.count() == 0) {
            initEvents();
        }
    }

    private void initEvents(){
        List<Event> events = new ArrayList<>();
        String[] hallUuids = {"13752743-5291-4ba3-8321-09c5b4dbe79f", "94348ab3-ed93-4637-96f8-63412eae5de5", "0895c4b1-321d-43ff-af83-633e8b88dddd", "5a3ba439-29ff-41fc-8ad7-3a6650c64831", "345edcde-5ae2-4029-9822-b1695829a1b2"};
        String[] eventUuids = {"13752743-5291-4ba3-8321-09c5b4dbe79f", "94348ab3-ed93-4637-96f8-63412eae5de5", "0895c4b1-321d-43ff-af83-633e8b88dddd", "5a3ba439-29ff-41fc-8ad7-3a6650c64831", "345edcde-5ae2-4029-9822-b1695829a1b2"};
        boolean[] featuredArr = {false, false, false, true, true};
        boolean[] soldOUtArr = {false, false, false, false, true};

        for (int i = 1; i < 6; i++) {
            events.add(new Event()
                    .setUuid(UUID.fromString(eventUuids[i -1]))
                    .setDescription(String.format("Event # %d # Description", i))
                    .setName(String.format("Event @ %d @ Name", i))
                    .setDate(LocalDate.of(2024, 8, 20 + i))
                    .setTickets(10 * i)
                    .setFeatured(featuredArr[i -1])
                    .setPhoneNumber(Integer.toString((i * 100) + (i * 10)) + i + "00000")
                    .setHallId(UUID.fromString(hallUuids[i - 1]))
                    .setUserId(UUID.randomUUID())
                    .setSoldOut(soldOUtArr[i-1])
            );
        }
        eventRepository.saveAll(events);
    }
}

