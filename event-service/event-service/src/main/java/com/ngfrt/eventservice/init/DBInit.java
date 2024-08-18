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
        String[] userUuids = {"13752743-5291-4ba3-8321-09c5b4dbe79f", "94348ab3-ed93-4637-96f8-63412eae5de5", "0895c4b1-321d-43ff-af83-633e8b88dddd", "5a3ba439-29ff-41fc-8ad7-3a6650c64831", "345edcde-5ae2-4029-9822-b1695829a1b2"};
        String[] eventDescriptions = {"The ultimate chess championship bringing together the world’s best players for a mind-bending showdown.", "Apex Industries unveils its latest groundbreaking technology at this exclusive innovation summit.", "The South Johnson Football Team hosts a thrilling match that promises high-energy and intense competition.", "John Petrov presents an inspiring talk on mastering productivity and achieving success in modern times.", "Michal Stevens Corporation celebrates its anniversary with a gala event, featuring top performers and exclusive presentations."};
        String[] eventOrganizers = {"FIDE", "Apex Industries", "South Johnson Football Team", "John Petrov", "Michal Stevens Corporation"};
        String[] eventNames = {"World Chess Championship 2024", "Apex Innovation Summit", "South Johnson Football Showdown", "Mastering Productivity with John Petrov", "Michal Stevens Corporation Anniversary Gala"};
        boolean[] featuredArr = {false, false, false, true, true};
        boolean[] soldOUtArr = {false, false, false, false, true};

        for (int i = 1; i < 6; i++) {
            events.add(new Event()
                    .setUuid(UUID.fromString(eventUuids[i -1]))
                    .setDescription(eventDescriptions[i - 1])
                    .setName(eventNames[i - 1])
                    .setDate(LocalDate.of(2024, 8, 23 + i))
                    .setTickets(10 * i)
                    .setOrganizer(eventOrganizers[i -1])
                    .setFeatured(featuredArr[i -1])
                    .setPhoneNumber(Integer.toString((i * 100) + (i * 10)) + i + "00000")
                    .setHallId(UUID.fromString(hallUuids[i - 1]))
                    .setUserId(UUID.fromString(userUuids[i -1]))
                    .setSoldOut(soldOUtArr[i-1])
            );
        }

        String[] eventNames2 = {"Symphony Under the Stars", "Tech Giants Summit 2024", "Riverside Annual Marathon", "Meet the Author: Jane Doe", "Gourmet Culinary Expo"};
        String[] eventUuids2 = {UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString()};
        String[] eventDescriptions2 = {"A magical evening of classical music with performances by world-renowned orchestras.", "Tech Giants Summit: Industry leaders discuss the future of AI and blockchain technology.", "Join the Riverside Marathon for an exhilarating run along scenic routes with fellow athletes.", "An exclusive book signing and Q&A session with bestselling author Jane Doe.", "The Culinary Expo: A grand showcase of gourmet delights from top chefs around the globe."};
        String[] eventOrganizers2 = {"Global Music Association", "InnovateX Conference", "Riverside Athletic Club", "Jane Doe Publishing", "Gourmet Society"};
        boolean[] featuredArr2 = {true, false, false, true, false};
        boolean[] soldOUtArr2 = {false, false, false, false, true};

        for (int i = 1; i < 6; i++) {
            events.add(new Event()
                    .setUuid(UUID.fromString(eventUuids2[i -1]))
                    .setDescription(eventDescriptions2[i - 1])
                    .setName(eventNames2[i-1])
                    .setDate(LocalDate.of(2024, 9, 1 + i))
                    .setTickets(17 * i)
                    .setOrganizer(eventOrganizers2[i -1])
                    .setFeatured(featuredArr2[i -1])
                    .setPhoneNumber(Integer.toString((i * 100) + (i * 10)) + i + "00000")
                    .setHallId(UUID.fromString(hallUuids[i - 1]))
                    .setUserId(UUID.fromString(userUuids[i -1]))
                    .setSoldOut(soldOUtArr2[i-1])
            );
        }

        String[] eventNames3 = {"Global eSports Championship", "Mountain Serenity Yoga Retreat", "Astronomical Society Stargazing Night", "Modern Art Exhibition", "Annual Wine Tasting Gala"};
        String[] eventUuids3 = {UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString()};
        String[] eventDescriptions3 = {"A thrilling gaming tournament featuring top players from around the world competing in the latest eSports titles.", "Join us for a serene yoga retreat in the mountains, designed to rejuvenate your mind and body.", "Explore the wonders of space at the Astronomical Society’s annual stargazing event.", "An exclusive art exhibition showcasing modern masterpieces from emerging artists.", "The Annual Wine Tasting Gala: Sample exquisite wines from renowned vineyards around the globe."};
        String[] eventOrganizers3 = {"Global eSports League", "Mountain Serenity Retreat", "Astronomical Society", "Modern Art Collective", "Vineyard Masters Association"};
        boolean[] featuredArr3 = {false, true, false, true, false};
        boolean[] soldOUtArr3 = {false, false, false, true, false};

        for (int i = 1; i < 6; i++) {
            events.add(new Event()
                    .setUuid(UUID.fromString(eventUuids3[i -1]))
                    .setDescription(eventDescriptions3[i - 1])
                    .setName(eventNames3[i-1])
                    .setDate(LocalDate.of(2024, 9, 6 + i))
                    .setTickets(17 * i)
                    .setOrganizer(eventOrganizers3[i -1])
                    .setFeatured(featuredArr3[i -1])
                    .setPhoneNumber(Integer.toString((i * 100) + (i * 10)) + i + "00000")
                    .setHallId(UUID.fromString(hallUuids[i - 1]))
                    .setUserId(UUID.fromString(userUuids[i -1]))
                    .setSoldOut(soldOUtArr3[i-1])
            );
        }

        String[] eventUuids4 = {UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString()};
        String[] eventNames4 = {"International Film Festival", "Tech Innovators Expo", "National Wildlife Conference", "Urban Art Walk", "Global Entrepreneurs Summit"};
        String[] eventDescriptions4 = {"An expansive celebration of global cinema, showcasing films from emerging and established filmmakers.", "Discover the latest in technology and innovation at this premier industry expo.", "Join experts in conservation and wildlife for a deep dive into current issues and solutions.", "A vibrant tour of the city’s most dynamic urban art, featuring live murals and installations.", "Meet and network with top entrepreneurs from around the world at this exclusive summit."};
        String[] eventOrganizers4 = {"World Cinema Alliance", "Tech Expo International", "Wildlife Conservation Society", "Urban Art Collective", "Global Business Network"};
        boolean[] featuredArr4 = {true, false, true, false, true};
        boolean[] soldOUtArr4 = {false, false, true, false, false};

        for (int i = 1; i < 6; i++) {
            events.add(new Event()
                    .setUuid(UUID.fromString(eventUuids4[i -1]))
                    .setDescription(eventDescriptions4[i - 1])
                    .setName(eventNames4[i-1])
                    .setDate(LocalDate.of(2024, 9, 12 + i))
                    .setTickets(18 * i)
                    .setOrganizer(eventOrganizers4[i -1])
                    .setFeatured(featuredArr4[i -1])
                    .setPhoneNumber(Integer.toString((i * 100) + (i * 10)) + i + "00000")
                    .setHallId(UUID.fromString(hallUuids[i - 1]))
                    .setUserId(UUID.fromString(userUuids[i -1]))
                    .setSoldOut(soldOUtArr4[i-1])
            );
        }

        eventRepository.saveAll(events);
    }
}

