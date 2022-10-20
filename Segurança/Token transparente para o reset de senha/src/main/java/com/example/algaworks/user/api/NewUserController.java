package com.example.algaworks.user.api;

import com.example.algaworks.user.domain.UserEntity;
import com.example.algaworks.user.domain.UserRepository;
import com.example.algaworks.user.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/public/user")
@RequiredArgsConstructor
public class NewUserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Valid MyUserRegisterRequest request){
        UserEntity user = request.toEntity();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = this.userRepository.save(user);
        return UserResponse.of(user);
    }
}
