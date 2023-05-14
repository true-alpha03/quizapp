package com.bmd.learnspringboot.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncodersAndHashingService {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String generateResetToken(String username){
        return passwordEncoder.encode(username);
    }

}
