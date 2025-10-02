package com.minJournalBackend.minJournalBackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
public class JournalEntry {

    @Id

    private String id;
    private String note;
    private JournalMood moodStatus; // status on moods enum
    private LocalDateTime createdAt;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
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


}
