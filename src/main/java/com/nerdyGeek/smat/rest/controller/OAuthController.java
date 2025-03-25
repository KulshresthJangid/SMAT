package com.nerdyGeek.smat.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nerdyGeek.smat.configs.GlobalProperties;
import com.nerdyGeek.smat.enums.SMPlatform;
import com.nerdyGeek.smat.services.FacebookService;
import com.nerdyGeek.smat.sql.repositories.UserRepository;

@RestController
@RequestMapping("/oauth2")
public class OAuthController {

	private GlobalProperties config;
	private UserRepository userRepository;
	private FacebookService fbService;

	@Autowired
	public OAuthController(GlobalProperties config, UserRepository userRepository, FacebookService fbService) {
		this.config = config;
		this.userRepository = userRepository;
		this.fbService = fbService;
	}

	@GetMapping("/connect/{platform}")
	public ResponseEntity<Object> smPlatformLogin(@PathVariable(value = "platform") SMPlatform smPlatform) {
		StringBuilder oAuthURL = new StringBuilder();
		switch (smPlatform) {
		case instagram: {
			oAuthURL.append(config.getAuthUri());
			oAuthURL.append("?response_type=" + config.getAuthorizationGrantType());
			oAuthURL.append("&client_id=" + config.getClientId());
			oAuthURL.append("&client_secret=" + config.getClientSecret());
			oAuthURL.append("&scope=" + config.getScope().toString());
			oAuthURL.append("&redirect_uri=" + config.getRedirectUri());
			return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, oAuthURL.toString()).build();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + smPlatform);
		}
	}

	@GetMapping("/callback/instagram")
	public ResponseEntity<Object> instagramLogin(@RequestParam(value = "access_token") String token) {
		String longLivedToken = fbService.getFacebookLongLivedTokenFromShortLivedToken(token);
		return null;
	}
}
