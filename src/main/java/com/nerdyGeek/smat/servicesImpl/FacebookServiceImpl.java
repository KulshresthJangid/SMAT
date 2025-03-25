package com.nerdyGeek.smat.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nerdyGeek.smat.configs.GlobalProperties;
import com.nerdyGeek.smat.dto.FacebookAPIResponseDTO;
import com.nerdyGeek.smat.feign.clients.FacebookFeignClient;
import com.nerdyGeek.smat.services.FacebookService;

@Service
public class FacebookServiceImpl implements FacebookService {

	@Autowired
	private FacebookFeignClient fbClient;

	@Autowired
	private GlobalProperties config;

	@Override
	public String getFacebookLongLivedTokenFromShortLivedToken(String shortLivedToken) {
		FacebookAPIResponseDTO fbResponse = fbClient
				.getFacebookLongLivedTokenFromShortLivedToken("ig_exchange_token", config.getClientId(),
						config.getClientSecret(), shortLivedToken)
				.getBody();
		return fbResponse.getAccessToken();
	}

}
