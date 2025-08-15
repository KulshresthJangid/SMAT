package com.nerdyGeek.smat.rest.controller;

import com.nerdyGeek.smat.entities.UserEntity;
import com.nerdyGeek.smat.services.CommonUtilityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Autowired
    private final CommonUtilityService commonUtilityService;

    public BaseController(CommonUtilityService commonUtilityService) {
        this.commonUtilityService = commonUtilityService;
    }

    public UserEntity getUser() {
        UserEntity userEntity = commonUtilityService.getUser();
        return userEntity;
    }
}
