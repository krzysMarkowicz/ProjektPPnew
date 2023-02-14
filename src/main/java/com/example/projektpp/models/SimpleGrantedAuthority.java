package com.example.projektpp.models;

import org.springframework.security.core.GrantedAuthority;

public final class SimpleGrantedAuthority implements GrantedAuthority {

    private final String role = "";

    @Override
    public String getAuthority() {
        return role;
    }
}
