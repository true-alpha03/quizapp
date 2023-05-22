package com.bmd.learnspringboot.model.quiz;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Getter
@Setter
@ToString
public class Question {
    private String q_id;
    private String question;
    private String type;
    private int score;
    private int negative;
    private List<Option> options;
    private int numofans;
    @JsonIgnore
    private List<String> answer;
}
