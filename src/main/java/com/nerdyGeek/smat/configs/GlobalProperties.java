package com.nerdyGeek.smat.configs;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class GlobalProperties {
    @Value("${instagram.client-id}")
    private String clientId;

    @Value("${instagram.client-secret}")
    private String clientSecret;

    @Value("${instagram.redirect-uri}")
    private String redirectUri;

    @Value("${instagram.scope}")
    private String scope;

    @Value("${instagram.authorization-uri}")
    private String authUri;

    @Value("${instagram.token-uri")
    private String tokenUri;

    @Value("${instagram.authorization-grant-type}")
    private String authorizationGrantType;

    @Value("${instagram.verification-token}")
    private String verificationToken;
}