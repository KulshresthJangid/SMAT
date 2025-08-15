package com.nerdyGeek.smat.services;

import com.nerdyGeek.smat.constants.Constants;
import com.nerdyGeek.smat.constants.Context;
import com.nerdyGeek.smat.constants.OpenAIConstants;
import com.nerdyGeek.smat.dto.OpenAIRequestDTO;
import com.nerdyGeek.smat.dto.OpenAIResponseDTO;
import com.nerdyGeek.smat.feign.clients.OpenAIFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apikey;

    private String apiKey;

    private final OpenAIFeignClient openAIAPICLient;

    public OpenAIService(OpenAIFeignClient openAIAPICLient) {
        this.openAIAPICLient = openAIAPICLient;
    }

    public String generateContent(String topic) {
        OpenAIRequestDTO request = new OpenAIRequestDTO("gpt-3.5-turbo", List.of(new OpenAIRequestDTO.Message(OpenAIConstants.MESSAGE_ROLE_SYSTEM, Context.AI_PERSONALITY), new OpenAIRequestDTO.Message(OpenAIConstants.MESSAGE_ROLE_USER, Context.AI_CONTENT.replace(OpenAIConstants.TOPIC_PLACEHOLDER, topic))), 0.7, 150);
        OpenAIResponseDTO response = openAIAPICLient.createChatCompletion(Constants.API_HEADER_BEARER + apiKey, Constants.API_HEADER_CONTENT_TYPE, request);
        return response.getChoices().get(0).getMessage().getContent();
    }

}
