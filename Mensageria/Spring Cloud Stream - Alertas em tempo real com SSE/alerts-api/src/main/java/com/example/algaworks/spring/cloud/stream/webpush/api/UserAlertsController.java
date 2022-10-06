package com.example.algaworks.spring.cloud.stream.webpush.api;

import com.example.algaworks.spring.cloud.stream.webpush.domain.user.User;
import com.example.algaworks.spring.cloud.stream.webpush.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserAlertsController {

    private final SecurityService securityService;

    @GetMapping(value = "/user/me")
    public User getUser() {
        return securityService.getAuthenticatedUser();
    }
}
