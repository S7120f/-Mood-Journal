package com.minJournalBackend.minJournalBackend.dto;

import com.minJournalBackend.minJournalBackend.model.JournalMood;

public class MoodStatistic {

    private JournalMood mood;
    private double percentage;

    public MoodStatistic(JournalMood mood, double percentage) {
        this.mood = mood;
        this.percentage = percentage;
    }

    public JournalMood getMood() {
        return mood;
    }

    public void setMood(JournalMood mood) {
        this.mood = mood;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
