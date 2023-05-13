package com.bmd.learnspringboot.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public
class LoginRequestBody {
    @JsonProperty("username")
    String username;

    @JsonProperty("pass")
    String pass;
}
