package com.minJournalBackend.minJournalBackend.service;


import com.minJournalBackend.minJournalBackend.dto.JournalEntryDto;
import com.minJournalBackend.minJournalBackend.dto.MoodStatistic;
import com.minJournalBackend.minJournalBackend.model.JournalEntry;
import com.minJournalBackend.minJournalBackend.model.JournalMood;
import com.minJournalBackend.minJournalBackend.model.User;
import com.minJournalBackend.minJournalBackend.repository.JournalEntryRepository;
import com.minJournalBackend.minJournalBackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JournalEntryService {


    private final UserRepository userRepository;
    private  final JournalEntryRepository journalEntryRepository;

    public JournalEntryService(UserRepository userRepository, JournalEntryRepository journalEntryRepository) {
        this.userRepository = userRepository;
        this.journalEntryRepository = journalEntryRepository;
    }

    //create new note
    public JournalEntryDto createNote(String note, JournalMood moodStatus, String username) {
        //Get user based on username
        User user = userRepository.findByUsername(username).orElse(null);

        //Create Journal
        JournalEntry newJournalEntry = new JournalEntry();
        newJournalEntry.setNote(note);
        newJournalEntry.setMoodStatus(moodStatus);
        newJournalEntry.setCreatedAt(LocalDateTime.now());
        newJournalEntry.setUsername(username);
       // newJournalEntry.setUserId(user.getId());

        //save
        JournalEntry saved = journalEntryRepository.save(newJournalEntry);

        return new JournalEntryDto(saved.getId(),
                saved.getNote(),
                saved.getMoodStatus(),
                saved.getCreatedAt(),
                username);

    }

    // List all journals
    public List<JournalEntryDto> getAllJournalEntries(String username) {

        List<JournalEntry> entries = journalEntryRepository.findByUsername(username);

        return entries.stream()
                .map(entry -> new JournalEntryDto(
                        entry.getId(),
                        entry.getNote(),
                        entry.getMoodStatus(),
                        entry.getCreatedAt(),
                        entry.getUsername()
                )).toList();
    }


    // Find journal entires by date between two dates
    public List<JournalEntryDto> getEntriesBetweenDates(String username, LocalDateTime startDate, LocalDateTime endDate) {
        System.out.println("Looking for entries for user=" + username
                + " between " + startDate + " and " + endDate);
         List<JournalEntry> sortJournals = journalEntryRepository.findByUsernameAndCreatedAtBetween(username, startDate, endDate);

        System.out.println("Found " + sortJournals.size() + " entries");

         return sortJournals.stream()
                 .map(j -> new JournalEntryDto(
                         j.getId(),
                         j.getNote(),
                         j.getMoodStatus(),
                         j.getCreatedAt(),
                         j.getUsername()
                 ))
                 .collect(Collectors.toList());

    }



    // See percentage by date
    public List<MoodStatistic> calculateMoodStatistics(String username, LocalDateTime startDate, LocalDateTime endDate) {
        List<JournalEntry> entries = journalEntryRepository.findByUsernameAndCreatedAtBetween(username, startDate, endDate);

        long total = entries.size();

        if (total == 0) return List.of();  // no divided by zero possible

        return entries.stream()
                .collect(Collectors.groupingBy
                        (JournalEntry::getMoodStatus, // Take every JournalEntry in list and group up after its moodStatus
                                Collectors.counting())) // in every group, count every element
                .entrySet().stream() // convert the map to collection of (key, value) - Key(mood) value(integer -number of how many )
                .map(e -> new MoodStatistic(e.getKey(), (e.getValue() * 100) / total)) // convert every part to a new object
                .collect(Collectors.toList()); // Build list and return it
    }

    // See percentage for all moods by user
    public List<MoodStatistic> calculateMoodStatistics(String username) {
        List<JournalEntry> entries = journalEntryRepository.findByUsername(username);

        long total = entries.size();

        if (total == 0) return List.of();

        return entries.stream()
                .collect(Collectors.groupingBy(JournalEntry::getMoodStatus, Collectors.counting()))
                .entrySet().stream()
                .map(e -> new MoodStatistic(e.getKey(), (e.getValue() * 100) / total))
                .collect(Collectors.toList());
    }




}
