package com.nerdyGeek.smat.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.nerdyGeek.smat.dto.InstagramAPIResponseDTO;
import com.nerdyGeek.smat.dto.InstagramAuthRequestDTO;

import feign.Headers;
import feign.codec.Encoder;

@FeignClient(name = "instagramClient", url = "https://api.instagram.com", configuration = InstagramFeignConfiguration.class)
public interface InstgramFeignClients {
	@PostMapping(value = "/oauth/access_token", consumes = "application/x-www-form-urlencoded")
	@Headers("Content-Type: application/x-www-form-urlencoded")

	ResponseEntity<InstagramAPIResponseDTO> getAccessToken(InstagramAuthRequestDTO instagramAuthRequestDTO);

}

@Configuration
class InstagramFeignConfiguration {
	@Bean
	Encoder formEncoder() {
		return new feign.form.FormEncoder();
	}
}
