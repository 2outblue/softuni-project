package com.ngfrt.appmain.model.dto;

import java.util.UUID;

public class HallDTO {
    private UUID uuid;
    private String name;
    private Integer number;
    private Integer capacity;
    private String description;

    public UUID getUuid() {
        return uuid;
    }

    public HallDTO setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public HallDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public HallDTO setNumber(Integer number) {
        this.number = number;
        return this;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public HallDTO setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public HallDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
