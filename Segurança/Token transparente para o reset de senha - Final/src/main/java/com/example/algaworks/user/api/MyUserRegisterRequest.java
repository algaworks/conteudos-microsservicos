package com.example.algaworks.user.api;

import com.example.algaworks.user.domain.UserEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MyUserRegisterRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .name(name)
                .email(email)
                .type(UserEntity.Type.CUSTOMER)
                .build();
    }
}
