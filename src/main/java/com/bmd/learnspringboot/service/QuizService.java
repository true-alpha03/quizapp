package com.bmd.learnspringboot.service;

import com.bmd.learnspringboot.model.quiz.Quiz;
import com.bmd.learnspringboot.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    private QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getByCourse_id(String course_id) {
        return quizRepository.getQuizByCourse_id(course_id);
    }
    public Quiz getById(String id) {
        return quizRepository.findById(id).orElse(null);
    }

    //This function works once you are inside a particular course
    public List<Map<String, String>> getQuizInfoFromListOfQuizzes(List<Quiz> quizList){
        List<Map<String, String>> quizInfoList = new ArrayList<>();
        for (Quiz quiz : quizList) {
            Map<String, String> quizInfo = new HashMap<>();
            quizInfo.put("quiz_id", quiz.getId());
            quizInfo.put("quiz_name", quiz.getName());
            quizInfo.put("course_id", quiz.getCourse_id());
            quizInfo.put("quiz_description", quiz.getDescription());
            quizInfo.put("quiz_start_time", String.valueOf(quiz.getPublish_date()));
            quizInfo.put("quiz_end_time", String.valueOf(quiz.getClosing_date()));
            quizInfo.put("quiz_duration", String.valueOf(quiz.getDuration()));
            quizInfo.put("quiz_isPasswordProtected", String.valueOf(quiz.is_password_protected()));
            quizInfoList.add(quizInfo);
        }
        return quizInfoList;
    }

    public List<Map<String, String>> getQuizInfoForCalendar(List<Quiz> quizList){
        List<Map<String, String>> quizInfoList = new ArrayList<>();
        for (Quiz quiz : quizList) {
            Map<String, String> quizInfo = new HashMap<>();
            quizInfo.put("quiz_name", quiz.getName()+" - "+quiz.getCourse_id());
            quizInfo.put("quiz_start_time", String.valueOf(quiz.getPublish_date()));
            quizInfo.put("quiz_duration", String.valueOf(quiz.getDuration()));
            quizInfoList.add(quizInfo);
        }
        return quizInfoList;
    }
}