package com.bmd.learnspringboot.service;

import com.bmd.learnspringboot.model.Course;
import com.bmd.learnspringboot.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    public List<Course> findBySemester(String semester) {
        return courseRepository.findBySemester(semester);
    }

}
