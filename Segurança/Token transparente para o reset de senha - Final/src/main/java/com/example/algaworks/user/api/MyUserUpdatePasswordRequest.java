package com.example.algaworks.user.api;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MyUserUpdatePasswordRequest {
    @NotBlank
    private String password;
}
