package com.cfc.JournalApp.service;

import com.cfc.JournalApp.entity.User;
import com.cfc.JournalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    //create new user
    public void registerUser(User user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRoles(Set.of("ROLE_USER"));
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Signup denied", e);
        }
    }

    //Method for Register as Admin
    public void registerAdmin(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Set.of("ROLE_ADMIN", "ROLE_USER"));
        userRepository.save(user);
    }


    //Method for saving user details after update entries
    public void saveUser(User user) {
        userRepository.save(user);
    }


    //Method for finding all users
    public List<User> findAllUser() {
        return userRepository.findAll();
    }


    //Method for find user by userID
    public Optional<User> getUserById(ObjectId id) {
        return userRepository.findById(id);
    }


    //Method for delete user by username
    public void deleteUserByUsername(String username) {
        userRepository.deleteUserByUsername(username);
    }

}
