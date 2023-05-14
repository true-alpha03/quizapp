package com.bmd.learnspringboot.model.RequestBody;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResetPasswordRequestBody {
    @JsonProperty("resetToken")
    String resetToken;
    @JsonProperty("pass")
    String password;
}
