package com.example.algaworks.user.api;

import com.example.algaworks.user.domain.UserEntity;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private UserEntity.Type type;

    public UserResponse(Long id, String email, String name, UserEntity.Type type) {
        this.id = id;
        this.email = email;
        this.name = name;
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
