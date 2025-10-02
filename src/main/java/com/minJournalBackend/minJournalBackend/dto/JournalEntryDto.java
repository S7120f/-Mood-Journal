package com.minJournalBackend.minJournalBackend.dto;

import com.minJournalBackend.minJournalBackend.model.JournalMood;

import java.time.LocalDateTime;

public class JournalEntryDto {


    private String id;
    private String note;
    private JournalMood moodStatus;
    private LocalDateTime createdAt;
    private String username;

    public JournalEntryDto(String id, String note, JournalMood moodStatus, LocalDateTime createdAt, String username) {
        this.id = id;
        this.note = note;
        this.moodStatus = moodStatus;
        this.createdAt = createdAt;
        this.username = username;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public JournalMood getMoodStatus() {
        return moodStatus;
    }

    public void setMoodStatus(JournalMood moodStatus) {
        this.moodStatus = moodStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
