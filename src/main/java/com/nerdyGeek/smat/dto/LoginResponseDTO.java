package com.nerdyGeek.smat.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String username;
    private String email;
    private String token;
    private long expiresIn;
}
