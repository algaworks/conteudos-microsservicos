package com.example.algaworks.user.api;

import com.example.algaworks.user.domain.UserEntity;
import com.example.algaworks.user.domain.UserPasswordService;
import com.example.algaworks.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PasswordResetController {
    private final UserPasswordService userPasswordService;
    private final UserRepository userRepository;

    @PostMapping("/public/forgot-password")
    public void forgotPassword(@RequestBody @Valid PasswordResetInput input) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(input.getEmail());
        optionalUser.ifPresent(user -> {
            String token = userPasswordService.generateToken(user);
            //email
            System.out.println(token);
        });
    }

    @PostMapping("/public/change-password")
    public void changePassword(@RequestBody @Valid PasswordUpdateWithTokenInput input) {
        try {
            userPasswordService.changePassword(input.getPassword(), input.getToken());
        } catch (Exception e) {
            log.error("Erro ao alterar a senha usando token", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
