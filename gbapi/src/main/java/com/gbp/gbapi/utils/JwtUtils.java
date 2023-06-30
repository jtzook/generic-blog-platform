package com.gbp.gbapi.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.gbp.gbapi.models.UserDetailsImpl;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtils {

    private final String jwtSecret;
    private final long jwtExpirationMs;

    public JwtUtils(@Value("${spring.jwtSecret}") String jwtSecret,
                    @Value("${spring.jwtExpirationMs}") long jwtExpirationMs) {
        this.jwtSecret = jwtSecret;
        this.jwtExpirationMs = jwtExpirationMs;
    }

    public String generateJwtToken(Authentication authentication) throws JOSEException {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        Instant now = Instant.now();
        Date issuedAt = Date.from(now);
        Date expiration = Date.from(now.plus(Duration.ofMillis(jwtExpirationMs)));

        JWSSigner signer = new MACSigner(jwtSecret);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(userPrincipal.getUsername())
                .issueTime(issuedAt)
                .expirationTime(expiration)
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    public String getUserNameFromJwtToken(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(jwtSecret);

        if (signedJWT.verify(verifier)) {
            return signedJWT.getJWTClaimsSet().getSubject();
        } else {
            throw new JOSEException("Invalid JWT signature");
        }
    }

    public boolean validateJwtToken(String authToken) throws JOSEException, ParseException {
        try {
            SignedJWT signedJWT = SignedJWT.parse(authToken);
            JWSVerifier verifier = new MACVerifier(jwtSecret);

            return signedJWT.verify(verifier) && !signedJWT.getJWTClaimsSet().getExpirationTime().before(new Date());
        } catch (ParseException | JOSEException e) {
            throw new JOSEException("Invalid JWT signature");
        }
    }
}
