package com.nerdyGeek.smat.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
	private String token;

	private long expiresIn;
}
