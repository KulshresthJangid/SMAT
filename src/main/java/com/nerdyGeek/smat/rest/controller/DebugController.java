package com.nerdyGeek.smat.rest.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.nerdyGeek.smat.entities.UserEntity;
import com.nerdyGeek.smat.sql.repositories.UserRepository;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debug")
public class DebugController {

	private UserRepository userRepository;

	DebugController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@GetMapping
	public Map<String, Object> checkSession(OAuth2AuthenticationToken authentication) {

		List<UserEntity> users = userRepository.findAll();

		if (!Objects.nonNull(authentication)) {
			return Map.of("status", "User is NOT logged in");
		}
		OAuth2User user = authentication.getPrincipal();
		return Map.of("status", "User is Logged In", "attributes", user.getAttributes());
	}
}
