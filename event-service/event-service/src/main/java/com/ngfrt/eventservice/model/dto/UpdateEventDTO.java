package com.ngfrt.eventservice.model.dto;

public class UpdateEventDTO {


    //TODO - perhaps add ability to change date, which will be possible only with Admin privileges?
    private String description;
    private String name;

    private Integer tickets;
    private boolean featured;
    private String phoneNumber;
    private boolean soldOut;

    public String getDescription() {
        return description;
    }

    public UpdateEventDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public UpdateEventDTO setName(String name) {
        this.name = name;
        return this;
    }


    public Integer getTickets() {
        return tickets;
    }

    public UpdateEventDTO setTickets(Integer tickets) {
        this.tickets = tickets;
        return this;
    }

    public boolean isFeatured() {
        return featured;
    }

    public UpdateEventDTO setFeatured(boolean featured) {
        this.featured = featured;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UpdateEventDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public UpdateEventDTO setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
        return this;
    }
}
