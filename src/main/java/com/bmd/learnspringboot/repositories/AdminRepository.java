package com.bmd.learnspringboot.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.bmd.learnspringboot.model.Admin;


@Repository
public interface AdminRepository extends MongoRepository<Admin,String>{
    @Query("{ 'username' : ?0, 'pass' : ?1 }")
    List<Admin> findByusernameandpassword(String username, String password);
    Admin getAdminByUsername(String username);
}