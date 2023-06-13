package com.bmd.learnspringboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bmd.learnspringboot.model.AdminCourse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bmd.learnspringboot.model.Admin;
import com.bmd.learnspringboot.model.RequestBody.AdminRequestBody;
import com.bmd.learnspringboot.repositories.AdminRepository;


@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/faculty")
public class AdminController {

    @Autowired
    private final AdminRepository adminRepository;

    private AdminController (AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("/courses")
    private ResponseEntity<?> adminCourses(@RequestParam String username){
        Admin admin = adminRepository.getAdminByUsername(username);
        Map<String, List<AdminCourse>> res = new HashMap<>();
        res.put("courses", admin.getCourses());
        return ResponseEntity.status(200).body(res);
    }


    @PostMapping("/login")
    private ResponseEntity<?> adminLogin(@RequestBody final AdminRequestBody admin) {

        List<Admin> adminInfo = adminRepository.findByusernameandpassword(admin.getUsername(), admin.getPass());
        Map<String, String> res = new HashMap<>();
        if(adminInfo.size() != 0) {
            Admin admin1 = adminInfo.get(0);
            res.put("message", "Login Successful");
            res.put("name", admin1.getName());
            res.put("url",admin1.getUrl());
            return ResponseEntity.status(200).body(res);
        }
        else {
            res.put("message","Invalid username or password");
            return ResponseEntity.status(404).body(res);
    }





    }
}