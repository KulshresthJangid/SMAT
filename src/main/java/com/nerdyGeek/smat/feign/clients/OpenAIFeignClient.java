package com.nerdyGeek.smat.feign.clients;

import com.nerdyGeek.smat.dto.OpenAIRequestDTO;
import com.nerdyGeek.smat.dto.OpenAIResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "openai-client", url = "${openai.api.url}")
public interface OpenAIFeignClient {

    @PostMapping("/v1/chat/completions")
    OpenAIResponseDTO createChatCompletion(
            @RequestHeader("Authorization") String authHeader,
            @RequestHeader("Content-Type") String contentType,
            @RequestBody OpenAIRequestDTO request
    );

}
