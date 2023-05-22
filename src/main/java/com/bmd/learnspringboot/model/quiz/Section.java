package com.bmd.learnspringboot.model.quiz;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@ToString
@Setter
@Getter
public class Section {
    private String section_name;
    private int count;
    private List<Question> questions;
    // Getters and setters
}
