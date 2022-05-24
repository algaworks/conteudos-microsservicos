package com.algaworks.example.auth.user.api;

import com.algaworks.example.auth.user.domain.UserEntity;

import javax.validation.constraints.NotBlank;

public class MyUserUpdateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;

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

    public void update(UserEntity currentUser) {
        currentUser.setEmail(this.email);
        currentUser.setName(this.name);
    }
}
