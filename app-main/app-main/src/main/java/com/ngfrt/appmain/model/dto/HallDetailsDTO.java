package com.ngfrt.appmain.model.dto;

public class HallDetailsDTO {

    private String name;
    private Integer number;
    private String description;

    public String getName() {
        return name;
    }

    public HallDetailsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public HallDetailsDTO setNumber(Integer number) {
        this.number = number;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public HallDetailsDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
