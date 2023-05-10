package com.bmd.learnspringboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rstpwd")
public class ResetPasswdController {
    @GetMapping("/req")
    private ResponseEntity<String> requestResetMail(@RequestParam String uname) {
        if(uname.contains("a")){
            // Mail api
            return ResponseEntity.ok("sent");
        }
        else{
            return ResponseEntity.ok("incorrect username");
        }
    }
}
