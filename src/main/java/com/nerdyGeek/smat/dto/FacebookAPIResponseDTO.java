package com.nerdyGeek.smat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FacebookAPIResponseDTO {
	
	@JsonProperty("access_token")
	public String accessToken;
	
	@JsonProperty("token_type")
	public String tokenType;

	@JsonProperty("expires_in")
	public double expiresIn;
}
