package com.minJournalBackend.minJournalBackend.repository;

import com.minJournalBackend.minJournalBackend.model.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {

    List<JournalEntry> findByUsername(String username);



    List<JournalEntry> findByUsernameAndCreatedAtBetween(String username, LocalDateTime startDate, LocalDateTime endDate);


}
