package com.minJournalBackend.minJournalBackend.controller;

import com.minJournalBackend.minJournalBackend.dto.LoginDto;
import com.minJournalBackend.minJournalBackend.dto.UserDto;
import com.minJournalBackend.minJournalBackend.service.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserServices userServices;

    public AuthController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginDto loginDto) {
        UserDto user = userServices.login(loginDto);
        if (user != null) {
            return ResponseEntity.ok(user); // return userinfo
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
