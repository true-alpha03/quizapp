package com.bmd.learnspringboot.service;

import com.bmd.learnspringboot.model.quiz.Quiz;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class QuizServiceTest {

    private void processQuizList(List<Quiz> quizList, List<Map<String, String>> quizInfoList, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex && i < quizList.size(); i++) {
            Quiz quiz = quizList.get(i);
            Map<String, String> quizInfo = new HashMap<>();
            quizInfo.put("quiz_name", quiz.getName() + " - " + quiz.getCourse_id());
            quizInfo.put("quiz_start_time", String.valueOf(quiz.getPublish_date()));
            quizInfo.put("quiz_duration", String.valueOf(quiz.getDuration()));
            quizInfoList.add(quizInfo);
        }
    }

    @Test
    public void testGetQuizInfoForCalendar(){
        List<Map<String, String>> quizInfoList = new ArrayList<>();
        List<Quiz> quizList = Arrays.asList(
                new Quiz("quiz_1", "course_1", LocalDateTime.now(), 300),
                new Quiz("quiz_2", "course_3", LocalDateTime.now(), 240),
                new Quiz("quiz_3", "course_2", LocalDateTime.now(), 180),
                new Quiz("quiz_4", "course_4", LocalDateTime.now(), 120),
                new Quiz("quiz_5", "course_1", LocalDateTime.now(), 600),
                new Quiz("quiz_6", "course_2", LocalDateTime.now(), 360),
                new Quiz("quiz_7", "course_3", LocalDateTime.now(), 480),
                new Quiz("quiz_8", "course_4", LocalDateTime.now(), 300),
                new Quiz("quiz_9", "course_1", LocalDateTime.now(), 360),
                new Quiz("quiz_10", "course_2", LocalDateTime.now(), 240)
        );

        int batchSize = 5;
        for (int i = 0; i < quizList.size(); i += batchSize) {
            processQuizList(quizList, quizInfoList, i, i + batchSize);
        }

        assertEquals(10, quizInfoList.size());
        for (int i = 0; i < quizList.size(); i++) {
            Quiz quiz = quizList.get(i);
            Map<String, String> quizInfo = quizInfoList.get(i);
            String expectedQuizName = quiz.getName() + " - " + quiz.getCourse_id();
            String actualQuizName = quizInfo.get("quiz_name");
            assertEquals(expectedQuizName, actualQuizName);
        }
    }

    @Test
    public void FalseTestGetQuizInfoForCalendar(){
        List<Map<String, String>> quizInfoList = new ArrayList<>();
        List<Quiz> quizList = Arrays.asList(
                new Quiz("quiz_1", "course_1", LocalDateTime.now(), 300),
                new Quiz("quiz_2", "course_3", LocalDateTime.now(), 240),
                new Quiz("quiz_3", "course_2", LocalDateTime.now(), 180),
                new Quiz("quiz_4", "course_4", LocalDateTime.now(), 120),
                new Quiz("quiz_5", "course_1", LocalDateTime.now(), 600),
                new Quiz("quiz_6", "course_2", LocalDateTime.now(), 360),
                new Quiz("quiz_7", "course_3", LocalDateTime.now(), 480),
                new Quiz("quiz_8", "course_4", LocalDateTime.now(), 300),
                new Quiz("quiz_9", "course_1", LocalDateTime.now(), 360),
                new Quiz("quiz_10", "course_2", LocalDateTime.now(), 240)
        );

        int batchSize = 5;
        for (int i = 0; i < quizList.size(); i += batchSize) {
            processQuizList(quizList, quizInfoList, i, i + batchSize);
        }

        assertEquals(10, quizInfoList.size());
        for (int i = 0; i < quizList.size(); i++) {
            Quiz quiz = quizList.get(i);
            Map<String, String> quizInfo = quizInfoList.get(i);
            String expectedQuizName = quiz.getName() + "- " + quiz.getCourse_id();
            String actualQuizName = quizInfo.get("quiz_name");
            assertNotEquals(expectedQuizName, actualQuizName);
        }
    }

}