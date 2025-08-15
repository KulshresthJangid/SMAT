package com.nerdyGeek.smat.rest.controller;


import com.nerdyGeek.smat.dto.APIResponseDTO;
import com.nerdyGeek.smat.dto.ContentDTO;
import com.nerdyGeek.smat.enums.SMPlatform;
import com.nerdyGeek.smat.services.CommonUtilityService;
import com.nerdyGeek.smat.services.OpenAIService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
public class ContentController extends BaseController {

    private OpenAIService openAIService;

    public ContentController(CommonUtilityService commonUtilityService, OpenAIService openAIService) {
        super(commonUtilityService);
        this.openAIService = openAIService;
    }


    @PostMapping("/post/${platform}")
    public APIResponseDTO<?> postContent(@PathVariable(value = "platform", required = true) SMPlatform platform, @RequestBody ContentDTO contentDTO) {
        APIResponseDTO<String> apiResponseDTO = new APIResponseDTO<>();
        String openAIResponse = openAIService.generateContent(contentDTO.topic);
        apiResponseDTO.setBody(openAIResponse);
        apiResponseDTO.setHttpStatus(HttpStatus.OK);
        return apiResponseDTO;
    }

}
