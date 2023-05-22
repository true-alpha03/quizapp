package com.bmd.learnspringboot;

import com.bmd.learnspringboot.model.quiz.Quiz;
import com.bmd.learnspringboot.service.LoginService;
import com.bmd.learnspringboot.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// This controller can be used for testing any functionality.

@RestController
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);
    private final LoginService loginService;
    @Autowired
    private QuizService quizService;
    @Autowired
    public TestController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/test/{course_id}")
    private ResponseEntity<?> getQuizzes(@PathVariable final String course_id) {
        List<Quiz> quizList = quizService.getByCourse_id(course_id);
        if(quizList.size()>0){
            List<Map<String, String>> quizOfCourse = quizService.getQuizInfoFromListOfQuizzes(quizList);
            return ResponseEntity.status(200).body(quizOfCourse);
        }else{
            HashMap<String,String> resp = new HashMap<>();
            resp.put("message","No quizzes found");
            return  ResponseEntity.status(404).body(resp);
        }
    }
}
