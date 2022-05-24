package com.algaworks.example.auth.post.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserReactiveClient {

    private WebClient webClient;
    private UserClientProperties userClientProperties;

    public UserReactiveClient(WebClient webClient, UserClientProperties userClientProperties) {
        this.webClient = webClient;
        this.userClientProperties = userClientProperties;
    }

    public Mono<UserResponse> findById(Long id) {
        return webClient.get()
                .uri(userClientProperties.getUrl() + "/users/{id}", id)
                .retrieve()
                .bodyToMono(UserResponse.class);
    }
}
