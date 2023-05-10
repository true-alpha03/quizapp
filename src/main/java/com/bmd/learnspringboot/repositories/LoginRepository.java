package com.bmd.learnspringboot.repositories;

import com.bmd.learnspringboot.model.Login;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LoginRepository extends MongoRepository<Login, String> {
    Optional<Login> getByUsername(String username);
}
