package com.nerdyGeek.smat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;

@SpringBootApplication
public class SmatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmatApplication.class, args);
	}

}
