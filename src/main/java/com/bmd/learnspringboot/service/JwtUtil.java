package com.bmd.learnspringboot.service;

import com.bmd.learnspringboot.controller.LoginRequestBody;
import com.bmd.learnspringboot.model.Login;
import com.bmd.learnspringboot.repositories.LoginRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Component


public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private String createToken(Map<String, Object> claims, List<String> subject) {
        long now = System.currentTimeMillis();
        Date validity = new Date(now); // 1 hour
        return Jwts.builder().setClaims(claims).setSubject(subject.toString()).setIssuedAt(new Date(now))
                .setExpiration(validity).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
    public String generateToken(LoginRequestBody login) {
        Map<String, Object> claims = new HashMap<>();
        List<String> a = new ArrayList<String>();
        a.add(login.getUsername());
        a.add(login.getPass());
        System.out.println("pass : " + login.getPass());

        return createToken(claims, a);
    }


}
