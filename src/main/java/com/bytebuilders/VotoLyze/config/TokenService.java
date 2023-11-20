package com.bytebuilders.VotoLyze.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bytebuilders.VotoLyze.entidades.Eleitor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.bytebuilders.VotoLyze.entidades.Politico;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * @author eduardo
 */
@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Eleitor usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("votolyze").
                    withSubject(usuario.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);

            return token;

        } catch (JWTCreationException exc) {
            throw new RuntimeException("JWT generation error - " + exc);
        }

    }


    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("votolyze").build().verify(token).getSubject();
        } catch (JWTVerificationException exc) {
            return "";

        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getUserIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);

            return jwt.getSubject(); // Assuming the subject is the user ID or email

        } catch (Exception e) {
            // Handle token verification errors
            e.printStackTrace();
            return null;
        }
    }


    public String extractToken(String authorizationHeader) {

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extracting the token after "Bearer "
            return authorizationHeader.substring(7); // 7 is the length of "Bearer "
        }

        return null; // No token found in the Authorization header
    }


    public String politicoGenerateToken(Politico politico) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("votolyze").
                    withSubject(politico.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);

            return token;

        } catch (JWTCreationException exc) {
            throw new RuntimeException("JWT generation error - " + exc);
        }

    }
}
