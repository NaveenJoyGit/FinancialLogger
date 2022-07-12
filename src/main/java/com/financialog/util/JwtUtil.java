package com.financialog.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.financialog.constants.ApplicationConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${application.jwt.secret}")
    private String secret;

    public static final String SUBJECT = "User Details";

    public String generateToken(String password) throws IllegalArgumentException {
        return JWT
                .create()
                .withSubject(SUBJECT)
                .withClaim("password", password)
                .withIssuedAt(new Date())
                .withIssuer(ApplicationConstants.APPLICATION_NAME)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject(SUBJECT)
                .withIssuer(ApplicationConstants.APPLICATION_NAME)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("password").asString();
    }

}
