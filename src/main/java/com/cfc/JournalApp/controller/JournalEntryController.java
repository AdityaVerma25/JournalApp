package com.cfc.JournalApp.controller;

import com.cfc.JournalApp.entity.JournalEntry;
import com.cfc.JournalApp.entity.User;
import com.cfc.JournalApp.repository.UserRepository;
import com.cfc.JournalApp.service.JournalEntryService;
import com.cfc.JournalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private final JournalEntryService journalEntryService;

    private final UserRepository userRepository;

    private final UserService userService;

    public JournalEntryController(JournalEntryService journalEntryService, UserRepository userRepository, UserService userService) {
        this.journalEntryService = journalEntryService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    //end-point for get all entry
    @GetMapping("/all-entry")
    public ResponseEntity<List<JournalEntry>> getAllEntry() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User userByUsername = userRepository.findUserByUsername(name);
        List<JournalEntry> journalEntries = userByUsername.getJournalEntries();
        if (!journalEntries.isEmpty()) {
            return new ResponseEntity<>(journalEntries, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // end-point for create entry
    @PostMapping("/create-entry")
    public ResponseEntity<?> creteEntry(@RequestBody JournalEntry entry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        journalEntryService.saveEntry(name, entry);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //end-point for update journal entry
    @PutMapping("/update-entry/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userByUsername = userRepository.findUserByUsername(authentication.getName());
        List<JournalEntry> journalEntries = userByUsername.getJournalEntries();
        if (!journalEntries.isEmpty()) {
            JournalEntry oldEntry = journalEntries.stream()
                    .filter(entry -> entry.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Journal entry not found"));
            oldEntry.setTitle(newEntry.getTitle());
            oldEntry.setContent(newEntry.getContent());
            journalEntryService.saveEntry(authentication.getName(), oldEntry);
            userService.saveUser(userByUsername);
        } else return new ResponseEntity<>("Entry not found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("Entry updated", HttpStatus.OK);
    }

    //end-point for get entry by ID
    @GetMapping("/get-entry/{id}")
    public ResponseEntity<JournalEntry> getEntryByd(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User userByUsername = userRepository.findUserByUsername(name);
        List<JournalEntry> journalEntries = userByUsername.getJournalEntries();
        if (!journalEntries.isEmpty()) {
            JournalEntry Entry = journalEntries.stream().filter(entry -> entry.getId().equals(id))
                    .findFirst().orElseThrow(() -> new RuntimeException("Entry not found"));
            return new ResponseEntity<>(Entry, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //end-point for delete entry by ID
    @DeleteMapping("/delete-entry/{id}")
    public ResponseEntity<?> deleteEntryByID(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User userByUsername = userRepository.findUserByUsername(name);
        userByUsername.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
        journalEntryService.deleteEntryById(id);
        userService.saveUser(userByUsername);
        return new ResponseEntity<>("Entry removed", HttpStatus.NOT_FOUND);
    }
}
