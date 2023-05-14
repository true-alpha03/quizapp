package com.bmd.learnspringboot.controller;


import com.bmd.learnspringboot.model.Login;
import com.bmd.learnspringboot.model.RequestBody.LoginRequestBody;
import com.bmd.learnspringboot.repositories.LoginRepository;
import com.bmd.learnspringboot.service.JwtUtil;
import lombok.Data;
import netscape.javascript.JSObject;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;


import javax.security.sasl.AuthenticationException;
import java.util.HashMap;


@RestController
@CrossOrigin
@RequestMapping("/student")
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;




    @Autowired
    private JwtUtil jwtUtil;




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
}
