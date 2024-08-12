package com.ngfrt.appmain.model.dto;

import java.util.UUID;

public class HallListingDTO {

    private Integer number;
    private UUID uuid;
    private String name;
    private String shortDescription;

    public String getName() {
        return name;
    }

    public HallListingDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public HallListingDTO setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
