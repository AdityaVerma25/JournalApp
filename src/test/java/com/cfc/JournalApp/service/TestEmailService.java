package com.cfc.JournalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestEmailService {
    @Autowired
    private EmailService emailService;

    @Disabled
    @Test
    void senEmail() {
        emailService.sendEmail("adityaverma3904@gmail.com", "Test case for, is email service running or not ?", "Email service running successfully");
    }
}
