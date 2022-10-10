package com.algaworks.example.spring.cloud.stream.app.manager.api.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AppUpdateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
}
