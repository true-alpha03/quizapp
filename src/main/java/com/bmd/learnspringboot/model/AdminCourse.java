package com.bmd.learnspringboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminCourse {
    private String course_id;
    private String course_name;
    private int semester;
    @Override
    public String toString(){
        return course_id+" "+course_name+" "+semester;
    }
}
