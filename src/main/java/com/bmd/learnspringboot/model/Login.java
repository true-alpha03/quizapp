package com.bmd.learnspringboot.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Document("login")
public class Login {

    @Id
    private String id;
    private String username;
    private String pass;

}
