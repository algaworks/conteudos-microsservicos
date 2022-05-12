package com.algaworks.example.mensagem.security;

import com.algaworks.example.mensagem.domain.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JPAUsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public JPAUsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email));
    }

}
