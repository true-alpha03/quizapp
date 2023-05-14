package com.bmd.learnspringboot.repositories;

import com.bmd.learnspringboot.model.Login;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LoginRepository extends MongoRepository<Login, String> {
    Optional<Login> getByUsername(String username);

    @Query("{ 'username' : ?0, 'pass' : ?1 }")
    List<Login> findByusernameandpassword(String username, String password);

    Optional<Login> getByResetToken(String resetToken);

}



