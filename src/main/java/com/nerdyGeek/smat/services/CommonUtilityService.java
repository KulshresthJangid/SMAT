package com.nerdyGeek.smat.services;

import com.nerdyGeek.smat.entities.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CommonUtilityService {

    public UserEntity getUser() {
        UserEntity userEntity = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.nonNull(authentication) && authentication.isAuthenticated() && authentication.getDetails() instanceof UserEntity) {
            userEntity = (UserEntity) (authentication.getDetails());
        }
        return userEntity;
    }
}
