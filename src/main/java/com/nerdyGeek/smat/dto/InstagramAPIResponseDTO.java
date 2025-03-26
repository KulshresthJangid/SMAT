package com.nerdyGeek.smat.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InstagramAPIResponseDTO {
	@JsonProperty("access_token")
	public String accessToken;

	@JsonProperty("user_id")
	public double userId;

	@JsonProperty("permissions")
	public List<String> permissions;
}
