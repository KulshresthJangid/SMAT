package com.nerdyGeek.smat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstagramAuthRequestDTO {
    private String client_id;
    private String client_secret;
    private String grant_type;
    private String redirect_uri;
    private String code;
}
