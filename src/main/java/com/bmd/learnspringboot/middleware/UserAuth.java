package com.bmd.learnspringboot.middleware;


import com.bmd.learnspringboot.service.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserAuth implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(UserAuth.class);
    public Boolean returnVar = false;

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String username = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        logger.debug(username);
        logger.debug(jwtUtil.userTokenList.get(username));
        returnVar = (request.getHeaders("authorization").nextElement().substring(7)).equals(jwtUtil.userTokenList.get(username));
        return true;
    }
}
