package com.algaworks.example.auth.post.client;

public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private UserType type;

    public UserResponse(Long id, String email, String name, UserType type) {
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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

}
