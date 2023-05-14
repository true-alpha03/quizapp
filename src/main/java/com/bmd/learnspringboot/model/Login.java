package com.bmd.learnspringboot.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Document(collection = "login")
public class Login {

    @Id
    private String id;
    private String username;
    private String pass;
    private String resetToken;
    private LocalDateTime tokenCreationTime;
    //private LocalDateTime tokenCreationTime;

    public Login(String username, String pass, String resetToken, LocalDateTime tokenCreationTime) {
        this.username = username;
        this.pass = pass;
        this.resetToken = resetToken;
        this.tokenCreationTime = tokenCreationTime;
    }
}

