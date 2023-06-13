package com.bmd.learnspringboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodersAndHashingServiceTest {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final EncodersAndHashingService encodersAndHashingService = new EncodersAndHashingService();

    @Test
    public void testGenerateResetToken() {
        // Test that the reset token is a valid BCrypt hash of the username
        String username = "testuser";
        String resetToken = encodersAndHashingService.generateResetToken(username);
        assertTrue(passwordEncoder.matches(username, resetToken),
                "Reset token should be a BCrypt hash of the username");

        // Test that the reset token is unique for different usernames
        String username2 = "testuser2";
        String resetToken2 = encodersAndHashingService.generateResetToken(username2);
        assertFalse(resetToken.equals(resetToken2),
                "Reset tokens should be unique for different usernames");

        // Test that the reset token is not the same as the username
        assertFalse(resetToken.equals(username),
                "Reset token should not be the same as the username");
    }

    @Test
    public void testGenerateResetTokenWithNullUsername() {
        // Test that passing a null username throws an IllegalArgumentException
        String username = null;
        try {
            encodersAndHashingService.generateResetToken(username);
        } catch (IllegalArgumentException ex) {
            assertEquals("Username cannot be null", ex.getMessage(),
                    "IllegalArgumentException should be thrown with appropriate message");
        }
    }

    @Test
    public void testGenerateResetTokenWithEmptyUsername() {
        String username = "";
        try {
            encodersAndHashingService.generateResetToken(username);
        } catch (IllegalArgumentException ex) {
            assertEquals("Username cannot be empty", ex.getMessage(),
                    "IllegalArgumentException should be thrown with appropriate message");
        }
    }
}