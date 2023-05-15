package com.bmd.learnspringboot.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Document(collection = "Courses")
public class Course {

    @Id
    private String id;
    private String course_id;
    private String course_name;
    private String semester;

}
