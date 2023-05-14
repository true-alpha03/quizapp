package com.bmd.learnspringboot.service;

import com.bmd.learnspringboot.controller.LoginRequestBody;
import com.bmd.learnspringboot.model.Login;
import com.bmd.learnspringboot.repositories.LoginRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import io.jsonwebtoken.security.Keys;

import static io.jsonwebtoken.Jwts.*;



@Component


public class JwtUtil {

    public HashMap<String,String> userTokenList = new HashMap<>();

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String createToken(Map<String, Object> claims, List<String> subject) {
        long now = System.currentTimeMillis();
        Date validity = new Date(now); // 1 hour   //set later
        var userToken = builder().setClaims(claims).setSubject(subject.toString()).setIssuedAt(new Date(now))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        userTokenList.put(subject.get(0),userToken);
        return userToken;
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
