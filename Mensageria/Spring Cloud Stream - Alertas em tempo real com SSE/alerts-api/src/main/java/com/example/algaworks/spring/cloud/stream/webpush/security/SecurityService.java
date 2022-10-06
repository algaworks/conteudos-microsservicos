package com.example.algaworks.spring.cloud.stream.webpush.security;

import com.example.algaworks.spring.cloud.stream.webpush.domain.user.User;
import com.example.algaworks.spring.cloud.stream.webpush.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;

    public User getAuthenticatedUser() {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow();
    }

}
