package com.bmd.learnspringboot.repositories;

import com.bmd.learnspringboot.model.quiz.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface QuizRepository extends MongoRepository<Quiz, String> {
    @Query("{'course_id': ?0}")
    List<Quiz> getQuizByCourse_id(String course_id);
    List<Quiz> getQuizByPublish_dateBetween(LocalDateTime start, LocalDateTime end);
    Optional<Quiz> findById(String id);

}