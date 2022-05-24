package com.algaworks.example.auth.user.api;

import com.algaworks.example.auth.user.domain.UserEntity;

public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private UserEntity.Type type;

    public UserResponse() {
    }

    public UserResponse(Long id, String email, String name, UserEntity.Type type) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity.Type getType() {
        return type;
    }

    public void setType(UserEntity.Type type) {
        this.type = type;
    }

    public static UserResponse of(UserEntity user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getType()
        );
    }
}
