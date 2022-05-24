package com.algaworks.example.auth.user.api;

import com.algaworks.example.auth.user.domain.UserEntity;
import com.algaworks.example.auth.user.domain.UserRepository;
import com.algaworks.example.auth.user.security.CanEditMyUser;
import com.algaworks.example.auth.user.security.CanReadMyUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class MyUserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public MyUserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @CanReadMyUser
    @GetMapping
    public UserResponse me(@AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getClaims().get("sub").toString();

        return userRepository.findByEmail(email)
               .map(UserResponse::of)
               .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
    }

    @CanEditMyUser
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal Jwt jwt,
                        @RequestBody MyUserUpdateRequest request) {
        String email = jwt.getClaims().get("sub").toString();
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
        request.update(user);
        userRepository.save(user);
    }

    @CanEditMyUser
    @PutMapping("/update-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@AuthenticationPrincipal Jwt jwt,
                        @RequestBody MyUserUpdatePasswordRequest request) {
        String email = jwt.getClaims().get("sub").toString();
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Valid MyUserRegisterRequest request){
        UserEntity user = request.toEntity();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = this.userRepository.save(user);
        return UserResponse.of(user);
    }

}
