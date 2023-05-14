package com.bmd.learnspringboot.controller;


import com.bmd.learnspringboot.model.Login;
import com.bmd.learnspringboot.model.RequestBody.LoginRequestBody;
import com.bmd.learnspringboot.model.RequestBody.ResetPasswordRequestBody;
import com.bmd.learnspringboot.repositories.LoginRepository;
import com.bmd.learnspringboot.service.EmailService;
import com.bmd.learnspringboot.service.EncodersAndHashingService;
import com.bmd.learnspringboot.service.JwtUtil;
import com.bmd.learnspringboot.service.LoginService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;


@RestController
@RequestMapping("/student")
public class LoginController {
    private final LoginService loginService;
    private final EncodersAndHashingService encodersAndHashingService;
    private final LoginRepository loginRepository;
    private final JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private EmailService emailService;

    public LoginController(LoginService loginService, EncodersAndHashingService encodersAndHashingService, LoginRepository loginRepository, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.encodersAndHashingService = encodersAndHashingService;
        this.loginRepository = loginRepository;
        this.jwtUtil = jwtUtil;
    }

    /**
     * This function handles the login request
     * @param login model which has values username and password
     */
    @PostMapping("/login")
    public ResponseEntity<?> studentLogin(@RequestBody final LoginRequestBody login) {
        if(loginRepository.findByusernameandpassword(login.getUsername(),login.getPass()).size() != 0) {
            final String jwtToken = jwtUtil.generateToken(login);
            HashMap<String, String> res = new HashMap<>();
            res.put("secretToken",jwtToken);
            res.put("username",login.getUsername());
            res.put("password",login.getPass());
            return ResponseEntity.status(200).body(res);
        }
        else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    /**
     * This function handles the request when there is a request for reset password. The function send a mail with the reset password link
     * @param username username
     */
    @GetMapping("/reset")
    private ResponseEntity<?> requestResetMail(@RequestParam String username) {
        username = username.toUpperCase();
        Optional<Login> optionalLogin = loginService.getByUsername(username);
        if(optionalLogin.isPresent()){
            Login login = optionalLogin.get();
            LocalDateTime localDateTime = LocalDateTime.now();
            login.setResetToken(encodersAndHashingService.generateResetToken(username).replaceAll("&",""));
            login.setTokenCreationTime(localDateTime);
            loginRepository.save(login);
            logger.info("Going to send the mail");
            emailService.sendResetEmail(username+"@cb.students.amrita.edu");
            logger.info("Mail Sent Successfully");
            return ResponseEntity.status(200).body("Ok");
        }
        return ResponseEntity.status(404).body("Invalid Username");
    }

    @PostMapping("/resetpass")
    public ResponseEntity<?> updatePassword(@RequestBody final ResetPasswordRequestBody requestBody) {
        Optional<Login> optionalLogin = loginRepository.getByResetToken(requestBody.getResetToken());
        if(optionalLogin.isPresent()){
            Login login = optionalLogin.get();
            LocalDateTime startTime = login.getTokenCreationTime();
            LocalDateTime endTime = LocalDateTime.now();
            Duration duration = Duration.between(startTime,endTime);
            if(duration.toSeconds()<=600) {
                login.setPass(requestBody.getPassword());
                login.setResetToken("");
                loginRepository.save(login);
                return ResponseEntity.status(200).body("Password Updated");
            }
            login.setResetToken("");
            loginRepository.save(login);
            logger.debug("Token exists but Expired");
        }
        logger.debug("Token is Invalid");
        return ResponseEntity.status(404).body("Invalid Reset Link or Link Expired");

    }






}