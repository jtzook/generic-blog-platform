package com.gbp.api.auth;

import java.util.Set;

public class SignupRequest {
    private String username;
    private String password;
    private Set<String> userRoles;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getUserRoles() {
        return this.userRoles;
    }

    public void setRoles(Set<String> userRoles) {
        this.userRoles = userRoles;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

