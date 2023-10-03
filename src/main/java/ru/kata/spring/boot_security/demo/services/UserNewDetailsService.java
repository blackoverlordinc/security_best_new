package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.kata.spring.boot_security.demo.models.User;

import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import ru.kata.spring.boot_security.demo.security.UserNewDetails;

import java.util.Optional;

@Service
public class UserNewDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserNewDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(s);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserNewDetails(user.get());
    }
}
