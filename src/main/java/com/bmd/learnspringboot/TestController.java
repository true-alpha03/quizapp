package com.bmd.learnspringboot;

import com.bmd.learnspringboot.model.Login;
import com.bmd.learnspringboot.service.LoginService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import org.slf4j.Logger;

// This controller can be used for testing any functionality.

@RestController
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);
    private final LoginService loginService;
    @Autowired
    public TestController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> fun(){
        return ResponseEntity.ok("Checking if logging works");
    }

}
