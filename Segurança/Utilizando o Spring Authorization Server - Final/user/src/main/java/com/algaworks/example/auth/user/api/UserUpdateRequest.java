package com.algaworks.example.auth.user.api;

import com.algaworks.example.auth.user.domain.UserEntity;

import javax.validation.constraints.NotBlank;

public class UserUpdateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private UserEntity.Type type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserEntity.Type getType() {
        return type;
    }

    public void setType(UserEntity.Type type) {
        this.type = type;
    }

    public void update(UserEntity currentUser) {
        currentUser.setEmail(this.email);
        currentUser.setName(this.name);
        currentUser.setType(this.type);
    }
}
