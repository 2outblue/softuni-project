package com.ngfrt.eventservice;

import com.ngfrt.eventservice.model.dto.EventDTO;
import com.ngfrt.eventservice.model.entity.Event;
import com.ngfrt.eventservice.model.mapper.EventMapper;
import com.ngfrt.eventservice.repository.EventRepository;
import com.ngfrt.eventservice.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EventServiceApplicationTests {
    @Mock
    private EventRepository repository;
    private EventService service;
    @Autowired
    private EventMapper mapper;
    private Event testEventOne;
    private Event testEventTwo;

    @BeforeEach
    public void init(){
        this.service=new EventService(this.repository,this.mapper);
        this.testEventOne=new Event();
        this.testEventTwo=new Event();

        this.testEventOne.setId(0L);
        this.testEventOne.setUuid(UUID.randomUUID());
        this.testEventOne.setDescription("Event one description");
        this.testEventOne.setName("Event One");
        this.testEventOne.setDate(LocalDate.now());
        this.testEventOne.setTickets(5);
        this.testEventOne.setFeatured(true);
        this.testEventOne.setPhoneNumber("000000000");
        this.testEventOne.setOrganizer("Ivan");
        this.testEventOne.setHallId(UUID.randomUUID());
        this.testEventOne.setUserId(UUID.randomUUID());
        this.testEventOne.setSoldOut(false);

        this.testEventTwo.setId(1L);
        this.testEventTwo.setUuid(UUID.randomUUID());
        this.testEventTwo.setDescription("Event two description");
        this.testEventTwo.setName("Event Two");
        this.testEventTwo.setDate(LocalDate.now());
        this.testEventTwo.setTickets(5);
        this.testEventTwo.setFeatured(true);
        this.testEventTwo.setPhoneNumber("1111111111");
        this.testEventTwo.setOrganizer("Pesho Peshov");
        this.testEventTwo.setHallId(UUID.randomUUID());
        this.testEventTwo.setUserId(UUID.randomUUID());
        this.testEventTwo.setSoldOut(false);
    }

    @Test
    public void testGetAllEvents() {
        when(this.repository.findAll()).thenReturn(List.of(this.testEventOne,this.testEventTwo));

        List<EventDTO> allEvents = this.service.getAllEvents();
        assertEquals("Event One", allEvents.get(0).getName());
        assertEquals(this.testEventOne.getUuid(), allEvents.get(0).getUuid());
        assertEquals("000000000", allEvents.get(0).getPhoneNumber());

        assertEquals("Event Two", allEvents.get(1).getName());
        assertEquals(this.testEventTwo.getUuid(), allEvents.get(1).getUuid());
        assertEquals("1111111111", allEvents.get(1).getPhoneNumber());
    }

    @Test
    public void testGetEventByUuid(){
        UUID fakeUUID= UUID.randomUUID();
        when(this.repository.findByUuid(this.testEventOne.getUuid())).thenReturn(Optional.of(this.testEventOne));
        when(this.repository.findByUuid(fakeUUID)).thenReturn(Optional.empty());

        Optional<EventDTO> resultOne = this.service.getEventByUuid(testEventOne.getUuid());
        Optional<EventDTO> resultTwo = this.service.getEventByUuid(fakeUUID);

        assertEquals("Event One", resultOne.get().getName());
        assertEquals(Optional.empty(),resultTwo);
    }

    @Test
    public void testBuyTicket(){
        UUID fakeUUID= UUID.randomUUID();
        when(this.repository.findByUuid(this.testEventOne.getUuid())).thenReturn(Optional.of(this.testEventOne));
        when(this.repository.findByUuid(fakeUUID)).thenReturn(Optional.empty());

        EventDTO resultOne = this.service.buyTicket(this.testEventOne.getUuid());
        EventDTO resultTwo = this.service.buyTicket(fakeUUID);

        assertEquals(6, resultOne.getTickets());
        assertNull(resultTwo);
    }
}
