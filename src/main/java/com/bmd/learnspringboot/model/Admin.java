package com.bmd.learnspringboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Document(collection = "admin")
public class Admin {
    @Id
    private String id;
    private String username;
    private String pass;
    private String course_id;

    public Admin(String username,String pass, String course_id) {
        this.username = username;
        this.pass = pass;
        this.course_id = course_id;
}
}