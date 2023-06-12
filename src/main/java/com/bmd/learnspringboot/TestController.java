package com.bmd.learnspringboot;

import com.bmd.learnspringboot.model.quiz.Question;
import com.bmd.learnspringboot.model.quiz.Quiz;
import com.bmd.learnspringboot.model.quiz.Section;
import com.bmd.learnspringboot.repositories.QuizRepository;
import com.bmd.learnspringboot.service.LoginService;
import com.bmd.learnspringboot.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

// This controller can be used for testing any functionality.

@RestController
@CrossOrigin
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);
    private final LoginService loginService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private QuizRepository quizRepository;
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
            return  ResponseEntity.status(200).body(new ArrayList<>());
        }
    }


    @GetMapping("/test")
    public ResponseEntity<?> fun(@RequestParam String quizId){
        Quiz quiz = quizService.getById(quizId);
        if(quiz != null){
            return ResponseEntity.ok(shuffleQuestions(quiz));
        }
        return ResponseEntity.status(500).body("Invalid Quiz id");
    }
    public Quiz shuffleQuestions(Quiz quiz){
        Map<String,List<String>> quizSolution = new HashMap<>();
        List<Section> sections = new ArrayList<>();
        for(Section section : quiz.getSections()){
            List<Question> questions = section.getQuestions();
            quizSolution.putAll(questions.stream()
                    .collect(Collectors.toMap(Question::getQ_id, Question::getAnswer)));
            Collections.shuffle(questions);
            section.setQuestions(questions.subList(0,section.getCount()));
            sections.add(section);
        }
        quiz.setSections(sections);
        return quiz;
    }

    @GetMapping("/test/calendar/getall")
    public  ResponseEntity<?> getAllQuiz(){
        return ResponseEntity.ok(quizService.getQuizInfoForCalendar(quizRepository.findAll()));
    }

}
