package com.fbieck.auth;

import org.springframework.security.core.GrantedAuthority;

public class UserAuthority implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "ROLE_USER";
    }
}
