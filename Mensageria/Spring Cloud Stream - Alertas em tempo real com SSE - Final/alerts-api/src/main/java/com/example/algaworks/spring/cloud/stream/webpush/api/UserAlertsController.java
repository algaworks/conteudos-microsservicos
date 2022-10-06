package com.example.algaworks.spring.cloud.stream.webpush.api;

import com.example.algaworks.spring.cloud.stream.webpush.domain.alert.AlertService;
import com.example.algaworks.spring.cloud.stream.webpush.domain.user.User;
import com.example.algaworks.spring.cloud.stream.webpush.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserAlertsController {

    private final SecurityService securityService;
    private final AlertService alertService;

    @GetMapping(value = "/user/me")
    public User getUser() {
        return securityService.getAuthenticatedUser();
    }

    @GetMapping("/user/me/alerts")
    public SseEmitter getAlerts() throws IOException {
        return alertService.watchAlerts();
    }
}
