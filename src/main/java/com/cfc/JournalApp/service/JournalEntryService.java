package com.cfc.JournalApp.service;

import com.cfc.JournalApp.entity.JournalEntry;
import com.cfc.JournalApp.entity.User;
import com.cfc.JournalApp.repository.JournalEntryRepository;
import com.cfc.JournalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    //Method for save journal Entry of any user
    @Transactional
    public void saveEntry(String username, JournalEntry entry) {
        try {
            User userByUsername = userRepository.findUserByUsername(username);
            entry.setDate(LocalDateTime.now());
            JournalEntry save = journalEntryRepository.save(entry);
            userByUsername.getJournalEntries().add(save);
            userService.saveUser(userByUsername);
        } catch (Exception e) {
            log.error("Exception occur while saving entry", e);
        }
    }


    //Getting all journal-entries
    public List<JournalEntry> getAllEntry() {
        return journalEntryRepository.findAll();
    }


    //Get entry by id
    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }


    //Delete entry by id
    public void deleteEntryById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }
}
