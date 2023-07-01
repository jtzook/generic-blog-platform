package com.gbp.api.utils;

import com.gbp.api.models.UserDetailsImpl;
import com.gbp.api.utils.JwtUtils;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.text.ParseException;

public class JwtUtilsTests {

    private JwtUtils jwtUtils;

    private String secretString = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private String testJwt = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODc1NTU4MjAsInN1YiI6ImV4cGVjdGVkLXVzZXIiLCJpYXQiOjE2ODc0Njk0MjB9.RyYgYQd3OKnNwkxBfIKXXahRwhwhoADzcKSzuD-t6jA";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        jwtUtils = new JwtUtils(this.secretString, 10000);
    }

    @Test
    public void testGenerateJwtToken() throws JOSEException, ParseException {
        UserDetailsImpl userDetails = new UserDetailsImpl(1L, "username", "password", null);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword());

        String jwtToken = jwtUtils.generateJwtToken(authentication);

        Assertions.assertNotNull(jwtToken);

        SignedJWT signedJWT = SignedJWT.parse(jwtToken);
        JWSVerifier verifier = new MACVerifier(this.secretString);

        Assertions.assertTrue(signedJWT.verify(verifier));
    }

    @Test
    public void testGetUserNameFromJwtToken() throws ParseException, JOSEException {
        String jwtToken = this.testJwt;

        String username = jwtUtils.getUserNameFromJwtToken(jwtToken);

        Assertions.assertEquals("expected-user", username);
    }

    @Test
    public void testValidateJwtToken() throws ParseException, JOSEException {
        String jwtToken = this.testJwt;

        boolean isValid = jwtUtils.validateJwtToken(jwtToken);

        Assertions.assertTrue(isValid);
    }
}
