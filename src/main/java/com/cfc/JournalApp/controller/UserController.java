package com.cfc.JournalApp.controller;

import com.cfc.JournalApp.entity.User;
import com.cfc.JournalApp.repository.UserRepository;
import com.cfc.JournalApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    //end-point for update user details
    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User userInDB = userRepository.findUserByUsername(name);
        userInDB.setUsername(user.getUsername());
        userInDB.setPassword(user.getPassword());
        userService.registerUser(userInDB);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteUserByUsername(authentication.getName());
        return new ResponseEntity<>("User deleted", HttpStatus.NO_CONTENT);

    }
}
