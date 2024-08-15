package com.ngfrt.eventservice;

import com.ngfrt.eventservice.model.entity.Event;
import com.ngfrt.eventservice.repository.EventRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc()
public class EventControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EventRepository repository;
    private Event testEvent;

    @BeforeEach
    public void init() {
        this.testEvent = this.repository.findByUuid(UUID.fromString("13752743-5291-4ba3-8321-09c5b4dbe79f"))
                .orElse(null);
    }

//    @AfterEach
//    public void destroy() {
//        this.repository.deleteAll();
//    }

    @Test
    public void testDBInit() {
        assertEquals("Event @ 1 @ Name", this.testEvent.getName());
        assertEquals(5, this.repository.findAll().size());
    }

    @Test
    public void testGetByUUID() throws Exception {
        this.mockMvc.perform(get("/api/event/13752743-5291-4ba3-8321-09c5b4dbe79f"))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/event/13752743-5291-4ba3-8321-09c5b4dbe123"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetByHallUUID() throws Exception {
        this.mockMvc.perform(get("/api/event/hall/94348ab3-ed93-4637-96f8-63412eae5de5"))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/event/hall/13752743-5291-4ba3-8321-09cssb4dbe123"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetFeatured() throws Exception {
        ResultActions expect = this.mockMvc.perform(get("/api/event/featured"))
                .andExpect(status().isOk());

        expect.andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
