package com.minJournalBackend.minJournalBackend.service;

import com.minJournalBackend.minJournalBackend.dto.LoginDto;
import com.minJournalBackend.minJournalBackend.dto.RegisterUserDto;
import com.minJournalBackend.minJournalBackend.dto.UserDto;
import com.minJournalBackend.minJournalBackend.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.minJournalBackend.minJournalBackend.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //log in function
    public UserDto login(LoginDto loginDto) {
        return userRepository.findByUsername(loginDto.getUsername())
                .filter(u -> passwordEncoder.matches(loginDto.getPassword(), u.getPassword()))
                .map(u -> new UserDto(u.getId(), u.getUsername()))
                .orElse(null);
    }

    //Create new User
    public UserDto createUser(LoginDto loginDto) {

        // check if user already exists
        if (userRepository.findByUsername(loginDto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(loginDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(loginDto.getPassword())); // password encoder
        User saved = userRepository.save(newUser);

        return new UserDto(saved.getId(), newUser.getUsername());
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()  // Stream our User so we only get id and username from our User Object
                .map(u -> new UserDto(u.getId(), u.getUsername()))
                .collect(Collectors.toList());
    }

}