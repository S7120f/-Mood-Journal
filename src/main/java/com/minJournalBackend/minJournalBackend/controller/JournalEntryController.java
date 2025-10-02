package com.minJournalBackend.minJournalBackend.controller;

import com.minJournalBackend.minJournalBackend.dto.JournalEntryDto;
import com.minJournalBackend.minJournalBackend.dto.MoodStatistic;
import com.minJournalBackend.minJournalBackend.payload.CreateNoteRequest;
import com.minJournalBackend.minJournalBackend.service.JournalEntryService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/journals")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular to access this API
public class JournalEntryController {

   private final JournalEntryService journalEntryService;

    public JournalEntryController(JournalEntryService journalEntryService) {
        this.journalEntryService = journalEntryService;
    }


    // Create note
    @PostMapping("/create-note")
    public JournalEntryDto createNote (@RequestBody CreateNoteRequest noteRequest) {
        return journalEntryService.createNote(noteRequest.getNote(), noteRequest.getMoodStatus(), noteRequest.getUsername());
    }

    //Get all notes for a user
    @GetMapping("/{username}")
    public List<JournalEntryDto> getAllJournalEntries(@PathVariable String username) {
        return journalEntryService.getAllJournalEntries(username);
    }

    //Find journal entries by date
    @GetMapping("/{username}/by-date")
    public List<JournalEntryDto> getEntriesByDate(@PathVariable String username, @RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return journalEntryService.getEntriesBetweenDates(username, startDate, endDate);
    }

    // statistics for moods

    @GetMapping("/{username}/stats")
    public List<MoodStatistic> getStatistics(@PathVariable String username, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate){

        if (startDate != null && endDate != null) {
            LocalDateTime start = LocalDateTime.parse(startDate);
            LocalDateTime end = LocalDateTime.parse(endDate);
            return journalEntryService.calculateMoodStatistics(username, start, end);
        } else {
            return journalEntryService.calculateMoodStatistics(username);
        }
    }


}
