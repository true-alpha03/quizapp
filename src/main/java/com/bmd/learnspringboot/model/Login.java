package com.bmd.learnspringboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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


    private String username;

    @JsonProperty("pass")
    private String pass;

}
