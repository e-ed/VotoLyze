/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bytebuilders.VotoLyze.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bytebuilders.VotoLyze.entidades.Eleitor;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author eduardo
 */

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    
    
    public String generateToken(Eleitor eleitor) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("votolyze").
                    withSubject(eleitor.getEmail()).withExpiresAt(1000).sign(algorithm);
            
        } catch() {
            
        }
        
    }
    
    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
    
}
