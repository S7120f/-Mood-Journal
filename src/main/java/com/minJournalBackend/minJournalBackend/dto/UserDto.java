package com.minJournalBackend.minJournalBackend.dto;

//DTO that sends to angular
public class UserDto {

    private Long id;
    private String username;

    public UserDto(String username, Long id) {
        this.username = username;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
