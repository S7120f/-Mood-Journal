package com.minJournalBackend.minJournalBackend.controller;

import com.minJournalBackend.minJournalBackend.dto.RegisterUserDto;
import com.minJournalBackend.minJournalBackend.dto.UserDto;
import org.springframework.web.bind.annotation.*;
import com.minJournalBackend.minJournalBackend.service.UserServices;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }


    //Register new user
    @PostMapping("/register")
    public UserDto register(@RequestBody RegisterUserDto userDto) {
        return userServices.createUser(userDto);
    }

    // get all user (without password)
    @GetMapping
    public List<UserDto> getAll(){
        return userServices.getAllUsers();
    }
}
