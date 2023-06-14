package com.jtzook.gbapi.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class User implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Set<String> roles;

    public User() {
    }

    public User(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    // Implement UserDetails methods

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert roles to GrantedAuthority objects
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify the logic as needed
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify the logic as needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify the logic as needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify the logic as needed
    }

    // Implement other UserDetails methods as needed

}
