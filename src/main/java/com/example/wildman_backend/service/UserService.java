package com.example.wildman_backend.service;


import com.example.wildman_backend.domain.exception.UserAlreadyExistsException;
import com.example.wildman_backend.domain.model.user.Role;
import com.example.wildman_backend.domain.model.user.User;
import com.example.wildman_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;





    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Transactional
    public User create(User user) {

        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());

        if(optionalUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
