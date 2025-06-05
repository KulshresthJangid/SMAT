package com.nerdyGeek.smat.rest.controller;

import com.nerdyGeek.smat.entities.UserEntity;
import com.nerdyGeek.smat.services.CommonUtilityService;
import jakarta.servlet.http.HttpServletRequest;

public class BaseController {

    private final CommonUtilityService commonUtilityService;

    BaseController(CommonUtilityService commonUtilityService) {
        this.commonUtilityService = commonUtilityService;
    }

    public UserEntity getUser() {
        UserEntity userEntity = commonUtilityService.getUser();
        return userEntity;
    }
}
