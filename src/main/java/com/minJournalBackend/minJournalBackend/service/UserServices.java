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
public class UserServices {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServices(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Create new User
    public UserDto createUser(RegisterUserDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword())); // password encoder
        User saved = userRepository.save(newUser);
        return new UserDto(saved.getUsername(), newUser.getId());
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()  // Stream our User so we only get id and username from our User Object
                .map(u -> new UserDto(u.getUsername(), u.getId()))
                .collect(Collectors.toList());
    }

    public UserDto login(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsername())
                .orElse(null);

        if (user != null && passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return new UserDto(user.getUsername(), user.getId());
        }
        return null;
    }
}
