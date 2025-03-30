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
import com.nerdyGeek.smat.interfaces.FacebookService;
import com.nerdyGeek.smat.interfaces.InstagramService;
import com.nerdyGeek.smat.sql.repositories.UserRepository;

@RestController
@RequestMapping("/oauth2")
public class OAuthController {

	private GlobalProperties config;
	private UserRepository userRepository;
	private FacebookService fbService;
	private InstagramService instaService;

	@Autowired
	public OAuthController(GlobalProperties config, UserRepository userRepository, FacebookService fbService,
			InstagramService instagramService) {
		this.config = config;
		this.userRepository = userRepository;
		this.fbService = fbService;
		this.instaService = instagramService;
	}

	@GetMapping("/connect/{platform}")
	public ResponseEntity<Object> smPlatformLogin(@PathVariable(value = "platform") SMPlatform smPlatform) {
		StringBuilder oAuthURL = new StringBuilder();
		switch (smPlatform) {
		case instagram: {
			oAuthURL.append(config.getAuthUri());
			oAuthURL.append("?response_type=" + config.getAuthorizationGrantType());
			oAuthURL.append("&client_id=" + config.getClientId());
			oAuthURL.append("&force_authentication=" + 1);
			oAuthURL.append("&enable_fb_login=" + 0);
			oAuthURL.append("&scope=" + config.getScope().toString());
			oAuthURL.append("&redirect_uri=" + config.getRedirectUri());
			return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, oAuthURL.toString()).build();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + smPlatform);
		}
	}

	@GetMapping("/callback/instagram")
	public ResponseEntity<Object> instagramLogin(@RequestParam(value = "code") String code) {
		String longLivedToken = instaService.getAccessTokenFromCode(code);
		return ResponseEntity.ok(longLivedToken);
	}

	@GetMapping("/webhook")
	public String verifyWebhook(@RequestParam("hub.mode") String mode, @RequestParam("hub.challenge") String challenge,
			@RequestParam("hub.verify_token") String verifyToken) {

		if ("subscribe".equals(mode) && "6350332180".equals(verifyToken)) {
			return challenge; // Respond with challenge token to verify
		} else {
			return "Invalid token"; // Verification failed
		}
	}
}
