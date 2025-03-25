package com.nerdyGeek.smat.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nerdyGeek.smat.dto.FacebookAPIResponseDTO;

@FeignClient(name = "fbClient", url = "https://graph.facebook.com/v22.0")
public interface FacebookFeignClient {
	@GetMapping("/oauth/access_token")
	ResponseEntity<FacebookAPIResponseDTO> getFacebookLongLivedTokenFromShortLivedToken(@RequestParam(value = "grant_type") String grantType,
			@RequestParam(value = "client_id") String clientId,
			@RequestParam(value = "client_secret") String clientSecret,
			@RequestParam(value = "fb_exchange_token") String token);
}
