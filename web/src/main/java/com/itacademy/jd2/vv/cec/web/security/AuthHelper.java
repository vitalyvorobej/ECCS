package com.itacademy.jd2.vv.cec.web.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public final class AuthHelper {

    private AuthHelper() {
    }

    public static Integer getLoggedUserId() {
        SecurityContext context = SecurityContextHolder.getContext();
        ExtendedUsernamePasswordAuthenticationToken authentication = (ExtendedUsernamePasswordAuthenticationToken) context
                .getAuthentication();
        return authentication.getId();
    }
}
