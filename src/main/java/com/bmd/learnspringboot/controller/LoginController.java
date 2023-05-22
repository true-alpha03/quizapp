package com.bmd.learnspringboot.controller;


import com.bmd.learnspringboot.model.Course;
import com.bmd.learnspringboot.model.Login;
import com.bmd.learnspringboot.model.RequestBody.LoginRequestBody;
import com.bmd.learnspringboot.model.RequestBody.ResetPasswordRequestBody;
import com.bmd.learnspringboot.repositories.CourseRepository;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/student")
@CrossOrigin
public class LoginController {
    private final LoginService loginService;
    private final EncodersAndHashingService encodersAndHashingService;
    private final LoginRepository loginRepository;
    private final JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private EmailService emailService;

    @Autowired
    private CourseRepository courseRepository;

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
    private ResponseEntity<?> studentLogin(@RequestBody final LoginRequestBody login) {
        List<Login> loginInfo = loginRepository.findByusernameandpassword(login.getUsername(),login.getPass());
        HashMap<String,Object> res = new HashMap<>();
        if(loginInfo.size() != 0) {
            final String jwtToken = jwtUtil.generateToken(login);
            res.put("secretToken",jwtToken);
            res.put("username",login.getUsername());
            res.put("name",loginInfo.get(0).getName());
            res.put("dp",loginInfo.get(0).getDp());
            return ResponseEntity.status(200).body(res);
        }
        else {
            res.put("message","Invalid username or password");
            return ResponseEntity.status(404).body(res);
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
        Map <String, String> res = new HashMap<>();
        if(optionalLogin.isPresent()){
            Login login = optionalLogin.get();
            LocalDateTime localDateTime = LocalDateTime.now();
            String resetToken = encodersAndHashingService.generateResetToken(username).replaceAll("&","");
            login.setResetToken(resetToken);
            login.setTokenCreationTime(localDateTime);
            loginRepository.save(login);
            logger.debug("Going to send the mail : "+username);
            emailService.sendResetEmail(username,resetToken);
            logger.debug("Mail Sent Successfully: "+username);
            res.put("message","Reset Link sent");
            return ResponseEntity.status(200).body(res);
        }
        res.put("message","Invalid username");
        return ResponseEntity.status(404).body(res);
    }

    @PostMapping("/resetpass")
    private ResponseEntity<?> updatePassword(@RequestBody final ResetPasswordRequestBody requestBody) {
        Optional<Login> optionalLogin = loginRepository.getByResetToken(requestBody.getResetToken());
        Map<String, String> res = new HashMap<>();
        if(optionalLogin.isPresent()){
            logger.info(requestBody.getResetToken());
            Login login = optionalLogin.get();
            LocalDateTime startTime = login.getTokenCreationTime();
            LocalDateTime endTime = LocalDateTime.now();
            Duration duration = Duration.between(startTime,endTime);
            logger.info(String.valueOf(duration.toSeconds()));
            if(duration.toSeconds()<=600) {
                login.setPass(requestBody.getPassword());
                login.setResetToken("");
                loginRepository.save(login);
                logger.info("Token is valid and password updated : "+requestBody.getResetToken());
                res.put("message","Updates password");
                return ResponseEntity.status(200).body(res);
            }
            res.put("message","Reset link expired");
            return ResponseEntity.status(408).body(res);
        }
        logger.info("Token is Invalid : "+requestBody.getResetToken());
        res.put("message","Invalid reset link");
        return ResponseEntity.status(400).body(res);

    }

    @GetMapping("/courses")
    private ResponseEntity<?> coursesInSemester(@RequestParam String semester){
        Optional<List<Course>> courseList= Optional.ofNullable(courseRepository.findBySemester(semester));
        Map<String, Object> res = new HashMap<>();
        if(courseList.isPresent()){
            if (courseList.get().size()>0){
                logger.debug("Courses found");
                res.put("courses",courseList.get());
                return ResponseEntity.status(200).body(res);
            }
        }
        logger.debug("Courses not found");
        res.put("message","Invalid semester");
        return ResponseEntity.status(404).body(res);
    }


}