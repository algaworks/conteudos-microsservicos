package com.algaworks.example.auth.user.api;


import com.algaworks.example.auth.user.domain.UserEntity;
import com.algaworks.example.auth.user.domain.UserRepository;
import com.algaworks.example.auth.user.security.CanReadUsers;
import com.algaworks.example.auth.user.security.CanWriteUsers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping
	@CanReadUsers
	public Page<UserResponse> findAll(Pageable pageable) {
		final Page<UserEntity> userEntityPage = userRepository.findAll(pageable);
		final var userResponses = userEntityPage.getContent()
				.stream()
				.map(UserResponse::of)
				.collect(Collectors.toList());
		return new PageImpl<>(userResponses, pageable, userEntityPage.getTotalElements());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CanWriteUsers
	public UserResponse create(@RequestBody @Valid UserRequest userRequest) {
		return UserResponse.of(userRepository.save(userRequest.toEntity()));
	}

	@GetMapping("/{id}")
	@CanReadUsers
	public UserResponse findById(@PathVariable Long id) {
		return UserResponse.of(userRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CanWriteUsers
	public UserResponse update(@PathVariable Long id,
	            @RequestBody UserUpdateRequest request) {
		UserEntity user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
		request.update(user);
		userRepository.save(user);
		return UserResponse.of(user);
	}
}
