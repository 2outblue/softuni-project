package com.ngfrt.appmain.model.dto;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.UUID;

public class EventInfoDTO {

    private UUID uuid;
    private String description;
    private String name;
    private LocalDate date;
    private String hallName;

    public String getDateString() {
        String monthName = getDate().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int day = getDate().getDayOfMonth();
        int year = getDate().getYear();
        return String.format("%s %d, %d",monthName, day, year);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }
}
