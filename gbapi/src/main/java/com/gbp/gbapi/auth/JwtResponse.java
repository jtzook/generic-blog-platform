package com.gbp.gbapi.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private Collection<? extends GrantedAuthority> userRoles;

    public JwtResponse(String accessToken, Long id, String username, Collection<? extends GrantedAuthority> collection) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.userRoles = collection;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getUserRoles() {
        return userRoles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> userRoles) {
        this.userRoles = userRoles;
    }
}


