package com.minJournalBackend.minJournalBackend.payload;

import com.minJournalBackend.minJournalBackend.model.JournalMood;

public class CreateNoteRequest {

    private String note;
    private JournalMood moodStatus;
    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
