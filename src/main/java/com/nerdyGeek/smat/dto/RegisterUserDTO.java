package com.nerdyGeek.smat.dto;

import com.nerdyGeek.smat.constants.Regex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterUserDTO {
    @NotNull(message = "Organization Id cannot be null")
    private Long orgId;

    @NotNull(message = "Username cannot be null")
    private String username;

    @NotNull
    private String password;

    @Pattern(regexp = Regex.EMAIL_REGEX ,message = "Invalid Email format")
    private String email;
}
