package com.bmd.learnspringboot.repositories;

import com.bmd.learnspringboot.model.quiz.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface QuizRepository extends MongoRepository<Quiz, String> {
    @Query("{'course_id': ?0}")
    List<Quiz> getQuizByCourse_id(String course_id);

}