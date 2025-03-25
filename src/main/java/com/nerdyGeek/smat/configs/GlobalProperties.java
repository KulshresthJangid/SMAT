package com.nerdyGeek.smat.configs;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "instagram")
@Data
public class GlobalProperties {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String scope;
    private String authUri;
    private String tokenUri;
    private String authorizationGrantType;
}