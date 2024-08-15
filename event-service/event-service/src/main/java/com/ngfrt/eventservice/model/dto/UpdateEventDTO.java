package com.ngfrt.eventservice.model.dto;

public class UpdateEventDTO {


    //TODO - perhaps add ability to change date, which will be possible only with Admin privileges?
    private String name;
    private String description;

    private String organizer;
    private String phoneNumber;


    public String getName() {
        return name;
    }

    public UpdateEventDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UpdateEventDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getOrganizer() {
        return organizer;
    }

    public UpdateEventDTO setOrganizer(String organizer) {
        this.organizer = organizer;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UpdateEventDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
