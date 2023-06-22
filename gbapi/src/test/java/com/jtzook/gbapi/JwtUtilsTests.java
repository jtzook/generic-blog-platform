package com.jtzook.gbapi;

import com.jtzook.gbapi.utils.JwtUtils;
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

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        jwtUtils = new JwtUtils();
    }

    // @Test
    // public void testGenerateJwtToken() throws JOSEException, ParseException {
    //     Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");

    //     String jwtToken = jwtUtils.generateJwtToken(authentication);

    //     Assertions.assertNotNull(jwtToken);

    //     SignedJWT signedJWT = SignedJWT.parse(jwtToken);
    //     JWSVerifier verifier = new MACVerifier("your-secret-key"); // Replace with your secret key

    //     Assertions.assertTrue(signedJWT.verify(verifier));
    // }

    // @Test
    // public void testGetUserNameFromJwtToken() throws ParseException, JOSEException {
    //     String jwtToken = "your-test-jwt-token"; // Replace with a valid JWT token

    //     String username = jwtUtils.getUserNameFromJwtToken(jwtToken);

    //     Assertions.assertEquals("expected-username", username);
    // }

    // @Test
    // public void testValidateJwtToken() throws ParseException, JOSEException {
    //     String jwtToken = "your-test-jwt-token"; // Replace with a valid JWT token

    //     boolean isValid = jwtUtils.validateJwtToken(jwtToken);

    //     Assertions.assertTrue(isValid);
    // }
}
