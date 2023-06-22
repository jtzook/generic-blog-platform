package com.jtzook.gbapi.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.jtzook.gbapi.auth.ERole;

import java.util.Collections;
import java.util.List;

public class UserDetailsImplTests {

    @Test
    public void testUserDetailsImpl() {
        Long testId = 1L;
        String testUsername = "test";
        String testPassword = "test";
        List<GrantedAuthority> testAuthorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        UserDetailsImpl userDetails = new UserDetailsImpl(testId, testUsername, testPassword, testAuthorities);

        assertEquals(testId, userDetails.getId());
        assertEquals(testUsername, userDetails.getUsername());
        assertEquals(testPassword, userDetails.getPassword());
        assertEquals(testAuthorities, userDetails.getAuthorities());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isEnabled());
    }

    @Test
    public void testEqualsAndHashCode() {
        Long testId = 1L;
        String testUsername = "test";
        String testPassword = "test";
        List<GrantedAuthority> testAuthorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        UserDetailsImpl userDetails1 = new UserDetailsImpl(testId, testUsername, testPassword, testAuthorities);
        UserDetailsImpl userDetails2 = new UserDetailsImpl(testId, testUsername, testPassword, testAuthorities);

        assertEquals(userDetails1, userDetails2);
        assertEquals(userDetails1.hashCode(), userDetails2.hashCode());
    }

    @Test
    public void testBuild() {
        User testUser = new User("test", "test");
        testUser.setId(1L);
        UserRole testRole = new UserRole(ERole.ROLE_USER);
        testUser.setRoles(Collections.singleton(testRole));

        UserDetailsImpl userDetails = UserDetailsImpl.build(testUser);

        assertEquals(testUser.getId(), userDetails.getId());
        assertEquals(testUser.getUsername(), userDetails.getUsername());
        assertEquals(testUser.getPassword(), userDetails.getPassword());
        assertEquals("ROLE_USER", userDetails.getAuthorities().iterator().next().getAuthority());
    }
}

