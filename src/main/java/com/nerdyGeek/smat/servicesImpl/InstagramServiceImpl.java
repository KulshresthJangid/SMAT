package com.nerdyGeek.smat.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nerdyGeek.smat.configs.GlobalProperties;
import com.nerdyGeek.smat.dto.InstagramAPIResponseDTO;
import com.nerdyGeek.smat.dto.InstagramAuthRequestDTO;
import com.nerdyGeek.smat.feign.clients.InstgramFeignClients;
import com.nerdyGeek.smat.services.InstagramService;

@Service
public class InstagramServiceImpl implements InstagramService {

	@Autowired
	private InstgramFeignClients instagramClient;

	@Autowired
	private GlobalProperties config;

	@Override
	public String getAccessTokenFromCode(String code) {
		InstagramAuthRequestDTO authDTO = new InstagramAuthRequestDTO();
		authDTO.setClient_id(config.getClientId());
		authDTO.setClient_secret(config.getClientSecret());
		authDTO.setCode(code);
		authDTO.setRedirect_uri(config.getRedirectUri());
		authDTO.setGrant_type("authorization_code");
		InstagramAPIResponseDTO instaAPIResp = instagramClient.getAccessToken(authDTO).getBody();
		return instaAPIResp.getAccessToken();
	}

}
