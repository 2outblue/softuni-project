package com.ngfrt.appmain.model.entity;

import jakarta.persistence.Entity;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity(name = "halls")
public class Hall extends BaseEntity{

    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;
    private String name;
    private Integer number;
    private Integer capacity;
    private String description;

    public UUID getUuid() {
        return uuid;
    }

    public Hall setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Hall setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public Hall setNumber(Integer number) {
        this.number = number;
        return this;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Hall setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Hall setDescription(String description) {
        this.description = description;
        return this;
    }
}
