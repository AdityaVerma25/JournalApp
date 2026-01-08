package com.cfc.JournalApp.controller;

import com.cfc.JournalApp.entity.User;
import com.cfc.JournalApp.service.UserDetailsServiceImpl;
import com.cfc.JournalApp.service.UserService;
import com.cfc.JournalApp.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtils;


    //end-point for check server health status
    @GetMapping("/health-status")
    public String healthStatus() {
        return "Everything is okay :)";
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signupUser(@RequestBody User user) {
        userService.registerUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String jwtToken = jwtUtils.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        } catch (AuthenticationException e) {
            log.error("Exception", e);
            return new ResponseEntity<>("Incorect username or password", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admin-signup")
    public ResponseEntity<User> signupAdmin(@RequestBody User user) {
        userService.registerAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
