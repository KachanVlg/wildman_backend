package com.example.wildman_backend.repository;

import com.example.wildman_backend.domain.model.user.Role;
import com.example.wildman_backend.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        // userRepository.deleteAll();
        // userRepository.save(User.builder()
        //         .role(Role.USER)
        //         .username("user")
        //         .password(encoder.encode("user")).build());
    }
}
