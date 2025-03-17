package com.biznex.utils;

import com.biznex.common.exception.AccessDeniedException;
import com.biznex.model.user.User;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class CurrentUser {

    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            return (User) authentication.getPrincipal();
        } catch (Exception e) {
            throw new AccessDeniedException("Token not valid {CurrentUser.class | row:21");
        }
    }


}
