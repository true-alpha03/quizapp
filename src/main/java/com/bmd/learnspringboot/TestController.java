package com.bmd.learnspringboot;

import com.bmd.learnspringboot.model.Login;
import com.bmd.learnspringboot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

// This controller can be used for testing any functionality.

@RestController
public class TestController {


    private final LoginService loginService;
    @Autowired
    public TestController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> fun(@RequestParam String username, String pass){
        return ResponseEntity.ok("working");
    }

}
