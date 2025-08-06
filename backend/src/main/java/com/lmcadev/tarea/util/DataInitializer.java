package com.lmcadev.tarea.util;

import com.lmcadev.tarea.model.user.User;
import com.lmcadev.tarea.repository.user.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("DataInitializer está funcionando");


        userRepository.deleteAll();

        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@example.com");
        admin.setFirstName("Admin");
        admin.setLastName("Root");
        admin.setRole(admin.getRole());
        admin.setPassword(passwordEncoder.encode("admin123"));

        userRepository.save(admin);
        System.out.println("Admin creado");
    }

}
