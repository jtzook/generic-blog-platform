package com.gbp.gbapi.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gbp.gbapi.auth.SignupRequest;
import com.gbp.gbapi.models.User;
import com.gbp.gbapi.models.UserDetailsImpl;
import com.gbp.gbapi.models.UserRole;
import com.gbp.gbapi.auth.ERole;
import com.gbp.gbapi.auth.JwtResponse;
import com.gbp.gbapi.auth.SigninRequest;
import com.gbp.gbapi.repositories.RoleRepository;
import com.gbp.gbapi.repositories.UserRepository;
import com.gbp.gbapi.utils.JwtUtils;

public class AuthControllerTests {

    @InjectMocks
    AuthController authController;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    PasswordEncoder encoder;

    @Mock
    JwtUtils jwtUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        // given
        SignupRequest signUpRequest = new SignupRequest();
        signUpRequest.setUsername("test");
        signUpRequest.setPassword("test");

        User user = new User("test", "test");
        UserRole userRole = new UserRole(ERole.ROLE_USER);

        // when
        when(userRepository.existsByUsername(any(String.class))).thenReturn(false);
        when(roleRepository.findByName(any(ERole.class))).thenReturn(Optional.of(userRole));
        when(encoder.encode(any(String.class))).thenReturn("test");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // then
        ResponseEntity<?> response = authController.registerUser(signUpRequest);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("User registered successfully!", response.getBody());
    }

    @Test
    void testAuthenticateUser() throws Exception {
        // given
        SigninRequest signinRequest = new SigninRequest();
        signinRequest.setUsername("test");
        signinRequest.setPassword("test");

        String testJwt = "testJwt";

        Set<UserRole> testRoles = new HashSet<>();
        testRoles.add(new UserRole(ERole.ROLE_USER));

        UserDetailsImpl testUserDetails = new UserDetailsImpl(1L, "test", "test", null);

        Authentication testAuth = new UsernamePasswordAuthenticationToken(testUserDetails, null, testUserDetails.getAuthorities());

        // when
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(testAuth);
        when(jwtUtils.generateJwtToken(any(Authentication.class))).thenReturn(testJwt);

        // then
        ResponseEntity<?> response = authController.authenticateUser(signinRequest);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody() instanceof JwtResponse);
        
        JwtResponse jwtResponseBody = (JwtResponse) response.getBody();
        assertNotNull(jwtResponseBody);
        assertEquals(testJwt, jwtResponseBody.getToken());
    }

}
