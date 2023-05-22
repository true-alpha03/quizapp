package com.bmd.learnspringboot.controller;


import com.bmd.learnspringboot.middleware.UserAuth;
import com.bmd.learnspringboot.model.quiz.Quiz;
import com.bmd.learnspringboot.repositories.LoginRepository;
import com.bmd.learnspringboot.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "", allowedHeaders = "", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/authStudent")
public class StudentController {

    private final LoginRepository loginRepository;

    private final UserAuth userAuth;

    private final QuizService quizService;

    @Autowired
    public StudentController(LoginRepository loginRepository,UserAuth userAuth, QuizService quizService) {
        this.loginRepository = loginRepository;
        this.userAuth = userAuth;
        this.quizService = quizService;
    }

    @GetMapping("/getDetails/{username}")
    private ResponseEntity<?> getStudentDetails(@PathVariable final String username) {
        System.out.println(userAuth.returnVar);
        if ((userAuth.returnVar).equals(true)) {

            return ResponseEntity.status(200).body(loginRepository.getByUsername(username));
        }
        else {

            HashMap<String,String> resp = new HashMap<>();
            resp.put("message","Unauthorized Access");
            return  ResponseEntity.status(401).body(resp);
        }
    }

    @GetMapping("/getQuizzes/{course_id}/{username}")
    private ResponseEntity<?> getQuizzes(@PathVariable final String course_id, @PathVariable final String username){
        if ((userAuth.returnVar).equals(true)) {
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
        else {

            HashMap<String,String> resp = new HashMap<>();
            resp.put("message","Unauthorized Access");
            return  ResponseEntity.status(401).body(resp);
        }

    }
}