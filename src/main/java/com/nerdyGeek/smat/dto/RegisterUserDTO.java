package com.nerdyGeek.smat.dto;

import lombok.Data;

@Data
public class RegisterUserDTO {
	private Long orgId;
	private String username;
	private String password;
	private String email;
}
