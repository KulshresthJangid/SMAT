package com.nerdyGeek.smat.dto;

import java.util.Objects;

import lombok.Data;

@Data
public class LoginUserDTO {
    private String email;
    private String username;
    private String password;

    public String getUsernameOrEmail() {
        if(Objects.isNull(this.email)) {
            return this.username;
        }
        return this.email;
    }
}
