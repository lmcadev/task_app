package com.lmcadev.tarea.service.auth;

import com.lmcadev.tarea.model.user.User;
import com.lmcadev.tarea.repository.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese correo"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), // o user.getUsername() si prefieres usar ese campo
                user.getPassword(),
                new ArrayList<>() // roles vacíos por ahora
        );
    }
}
