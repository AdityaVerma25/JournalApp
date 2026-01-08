package com.cfc.JournalApp.controller;

import com.cfc.JournalApp.entity.User;
import com.cfc.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all-user")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUser = userService.findAllUser();
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }
}
