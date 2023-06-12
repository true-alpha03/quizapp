package com.bmd.learnspringboot.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/login")

    private ResponseEntity<?> adminLogin(@RequestBody final AdminRequestBody admin) {

        List<Admin> adminInfo = adminRepository.findByusernameandpassword(admin.getUsername(), admin.getPass());
        HashMap<String,String> res = new HashMap<>();
        if(adminInfo.size() != 0) {
            res.put("message", "Login Successful");
            return ResponseEntity.status(200).body(res);
        }
        else {
            res.put("message","Invalid username or password");
            return ResponseEntity.status(404).body(res);
  }

}
}