package com.bmd.learnspringboot.controller;


import com.bmd.learnspringboot.middleware.UserAuth;
import com.bmd.learnspringboot.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/authStudent")
public class StudentController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UserAuth userAuth;

    @GetMapping("/getDetails/{username}")
    private ResponseEntity<?> getStudentDetails(@PathVariable final String username) {
        System.out.println(userAuth.returnVar);
        if ((userAuth.returnVar).equals(true)) {

            return ResponseEntity.status(200).body(loginRepository.getByUsername(username));
        }
        else {

            HashMap<String,String> resp = new HashMap<>();
            resp.put("message","Unauthorized Access");
            return  ResponseEntity.status(401).body(resp);
        }
    }
}
