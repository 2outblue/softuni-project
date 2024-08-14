package com.ngfrt.appmain.model.dto;

import java.util.UUID;

public class EventCalendarDTO {

    private String name;
    private boolean featured;
    private int dayOfMonth;
    private UUID hallId;

    public String getName() {
        return name;
    }

    public EventCalendarDTO setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isFeatured() {
        return featured;
    }

    public EventCalendarDTO setFeatured(boolean featured) {
        this.featured = featured;
        return this;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public EventCalendarDTO setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
        return this;
    }

    public UUID getHallId() {
        return hallId;
    }

    public EventCalendarDTO setHallId(UUID hallId) {
        this.hallId = hallId;
        return this;
    }
}
