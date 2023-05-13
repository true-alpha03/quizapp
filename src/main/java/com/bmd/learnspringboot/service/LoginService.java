package com.bmd.learnspringboot.service;

import com.bmd.learnspringboot.model.Login;
import com.bmd.learnspringboot.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    private final LoginRepository loginRepository;
    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
    public Optional<Login> getByUsername(String username) {
        return loginRepository.getByUsername(username);
    }

    public List<Login> findByusernameandpassword(String username, String password) {


        return loginRepository.findByusernameandpassword(username,password);
    }

}
