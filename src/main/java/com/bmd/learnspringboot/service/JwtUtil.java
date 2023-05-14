package com.bmd.learnspringboot.service;

import com.bmd.learnspringboot.model.RequestBody.LoginRequestBody;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

import static io.jsonwebtoken.Jwts.builder;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component


public class JwtUtil {

    public HashMap<String,String> userTokenList = new HashMap<>();

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private String createToken(Map<String, Object> claims, List<String> subject) {
        long now = System.currentTimeMillis();
        var userToken = builder().setClaims(claims).setSubject(subject.toString()).setIssuedAt(new Date(now))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        userTokenList.put(subject.get(0),userToken);
        return userToken;
    }
    public String generateToken(LoginRequestBody login) {
        Map<String, Object> claims = new HashMap<>();
        List<String> a = new ArrayList<>();
        a.add(login.getUsername());
        a.add(login.getPass());
        System.out.println("pass : " + login.getPass());

        return createToken(claims, a);
    }




}
