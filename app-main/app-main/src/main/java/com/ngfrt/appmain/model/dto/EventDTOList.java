package com.ngfrt.appmain.model.dto;

import java.util.ArrayList;
import java.util.List;

public class EventDTOList {
    private List<EventDTO> eventDTOList;

    public EventDTOList() {
        eventDTOList = new ArrayList<>();
    }

    public List<EventDTO> getEventDTOList() {
        return eventDTOList;
    }

    public EventDTOList setEventDTOList(List<EventDTO> eventDTOList) {
        this.eventDTOList = eventDTOList;
        return this;
    }
}
