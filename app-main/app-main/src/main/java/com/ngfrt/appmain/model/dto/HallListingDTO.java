package com.ngfrt.appmain.model.dto;

public class HallListingDTO {

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
}
