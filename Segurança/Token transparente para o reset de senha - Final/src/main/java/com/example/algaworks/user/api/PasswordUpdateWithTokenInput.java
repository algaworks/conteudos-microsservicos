package com.example.algaworks.user.api;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PasswordUpdateWithTokenInput {
    @NotBlank
    private String password;

    @NotBlank
    private String token;
}
