package com.minJournalBackend.minJournalBackend.controller;

import com.minJournalBackend.minJournalBackend.dto.LoginDto;
import com.minJournalBackend.minJournalBackend.dto.UserDto;
import com.minJournalBackend.minJournalBackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular to access this API
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginDto loginDto) {
        UserDto user = userService.login(loginDto);
        if (user != null) {
            return ResponseEntity.ok(user); // return userinfo
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginDto loginDto) {
        try {
            UserDto newUser = userService.createUser(loginDto);
            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/getUsers")
    public List<UserDto> getAll(){
        return userService.getAllUsers();
    }


}
