package com.algaworks.example.secure;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class Product {

    private UUID id;

    @NotBlank
    private String name;

    private String createdByUser;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }
}
