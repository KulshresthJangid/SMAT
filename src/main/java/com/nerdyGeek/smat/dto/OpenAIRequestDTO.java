package com.nerdyGeek.smat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OpenAIRequestDTO {
    private String model;
    private List<Message> messages;
    private double temperature;

    @JsonProperty("max_tokens")
    private int maxTokens;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Message {
        private String role;
        private String content;
    }
}