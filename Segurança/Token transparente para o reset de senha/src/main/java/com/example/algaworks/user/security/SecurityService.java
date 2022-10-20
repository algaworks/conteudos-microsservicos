package com.example.algaworks.user.security;

import com.example.algaworks.user.domain.UserEntity;
import com.example.algaworks.user.domain.UserEntityNotFoundException;
import com.example.algaworks.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;

    public UserEntity getAuthenticatedUser() {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(UserEntityNotFoundException::new);
    }

}
