package com.bmd.learnspringboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private List<AdminCourse> courses;
    private String name;
    private String url;

    @Override
    public String toString(){
        return username+" "+pass+" "+"\n"+courses+"\n "+name+" "+url;
    }
}