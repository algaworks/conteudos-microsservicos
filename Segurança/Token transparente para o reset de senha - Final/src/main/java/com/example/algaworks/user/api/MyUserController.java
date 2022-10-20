package com.example.algaworks.user.api;

import com.example.algaworks.user.domain.UserEntity;
import com.example.algaworks.user.domain.UserEntityNotFoundException;
import com.example.algaworks.user.domain.UserRepository;
import com.example.algaworks.user.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MyUserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityService securityService;

    @GetMapping
    public UserResponse me() {
        UserEntity user = securityService.getAuthenticatedUser();

        return userRepository.findByEmail(user.getEmail())
               .map(UserResponse::of)
               .orElseThrow(UserEntityNotFoundException::new);
    }

    @PutMapping("/update-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@RequestBody @Valid MyUserUpdatePasswordRequest request) {
        UserEntity user = securityService.getAuthenticatedUser();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

}
