package com.bmd.learnspringboot.model.quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "quiz")
@Getter
@Setter
@ToString
public class Quiz {
    @Id
    private String id;
    private String name;
    private String description;
    private String course_id;
    @JsonIgnore
    private String password;
    private boolean is_password_protected;
    private LocalDateTime publish_date;
    private LocalDateTime closing_date;
    private int duration;
    private List<Section> sections;
}