package com.example.algaworks.user.api;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class PasswordResetInput {
    @Email
    @NotBlank
    private String email;
}
