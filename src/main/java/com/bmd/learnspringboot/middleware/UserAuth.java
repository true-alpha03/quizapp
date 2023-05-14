package com.bmd.learnspringboot.middleware;


import com.bmd.learnspringboot.service.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.HashSet;

@Component
public class UserAuth implements HandlerInterceptor {

    public Boolean returnVar = false;

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Middleware");

        String username = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        System.out.println(username);
        System.out.println(jwtUtil.userTokenList.get(username));
        if((request.getHeaders("authorization").nextElement().substring(7)).equals(jwtUtil.userTokenList.get(username))) {


            returnVar = true;
        }
        else {
           returnVar = false;
        }

        return true;
    }
}
