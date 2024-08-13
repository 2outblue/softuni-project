package com.ngfrt.appmain.model.dto;

import com.ngfrt.appmain.model.entity.Hall;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.UUID;

public class TicketDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String eventName;
    private LocalDate eventDate;
    private String paymentInfo;
    private String cardCode;
    private String hallName;
    private UUID hallUuid;


    public String getDateString() {
        if (getEventDate() != null) {
            String monthName = getEventDate().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            int day = getEventDate().getDayOfMonth();
            int year = getEventDate().getYear();
            return String.format("%s %d, %d",monthName, day, year);
        }
        return "";
    }

    public String getFirstName() {
        return firstName;
    }

    public TicketDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public TicketDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public TicketDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEventName() {
        return eventName;
    }

    public TicketDTO setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public TicketDTO setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
        return this;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public TicketDTO setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
        return this;
    }

    public String getCardCode() {
        return cardCode;
    }

    public TicketDTO setCardCode(String cardCode) {
        this.cardCode = cardCode;
        return this;
    }

    public String getHallName() {
        return hallName;
    }

    public TicketDTO setHallName(String hallName) {
        this.hallName = hallName;
        return this;
    }

    public UUID getHallUuid() {
        return hallUuid;
    }

    public TicketDTO setHallUuid(UUID hallUuid) {
        this.hallUuid = hallUuid;
        return this;
    }
}
