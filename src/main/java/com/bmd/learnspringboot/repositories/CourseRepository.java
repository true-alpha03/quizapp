package com.bmd.learnspringboot.repositories;

import com.bmd.learnspringboot.model.Course;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface CourseRepository extends MongoRepository<Course,String> {

    @Query("{'semester' : ?0}")
    List<Course> findBySemester(String semester);
}
//course added with login