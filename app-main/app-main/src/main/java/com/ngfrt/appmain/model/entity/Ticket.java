package com.ngfrt.appmain.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "tickets")
public class Ticket extends BaseEntity {

    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;
    private String firstName;
    private String lastName;
    private LocalDate eventDate;
    private Integer seatingSection;

    @ManyToOne
    private Hall hallUuid;

    public UUID getUuid() {
        return uuid;
    }

    public Ticket setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Ticket setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Ticket setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public Ticket setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
        return this;
    }

    public Integer getSeatingSection() {
        return seatingSection;
    }

    public Ticket setSeatingSection(Integer seatingSection) {
        this.seatingSection = seatingSection;
        return this;
    }

    public Hall getHallUuid() {
        return hallUuid;
    }

    public Ticket setHallUuid(Hall hallUuid) {
        this.hallUuid = hallUuid;
        return this;
    }
}
