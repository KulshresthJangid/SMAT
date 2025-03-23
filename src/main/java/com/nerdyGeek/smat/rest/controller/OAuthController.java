package com.nerdyGeek.smat.rest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nerdyGeek.smat.entities.User;
import com.nerdyGeek.smat.sql.repositories.UserRepository;

@RestController
@RequestMapping("/oauth2/callback")
public class OAuthController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/instagram")
	public String instagramLogin(OAuth2AuthenticationToken token) {
		Map<String, Object> attributes = token.getPrincipal().getAttributes();
		String instagramId = (String) attributes.get("id");
		String username = (String) attributes.get("username");

		User user = userRepository.findByUsername(username);

		return "Instagram token saved " + instagramId;
	}
}
