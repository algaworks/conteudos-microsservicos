package com.example.algaworks.spring.cloud.stream.webpush.domain.user;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserRepository {

    private final static List<User> USERS = new ArrayList<>();

    static {
        User user1 = User.builder()
                .id(UUID.randomUUID())
                .email("joao@email.com")
                .password("{noop}123456")
                .name("João")
                .followedAuthors(Arrays.asList("Matt", "John"))
                .type(User.Type.CUSTOMER)
                .build();
        User user2 = User.builder()
                .id(UUID.randomUUID())
                .email("maria@email.com")
                .password("{noop}123456")
                .name("Maria")
                .followedAuthors(Arrays.asList("John"))
                .type(User.Type.CUSTOMER)
                .build();
        USERS.add(user1);
        USERS.add(user2);
    }

    public List<User> findAll() {
        return USERS;
    }

    public Optional<User> findByEmail(String email) {
        return USERS
                .stream()
                .filter(blogUser -> blogUser.getEmail().equals(email))
                .findFirst();
    }

}
