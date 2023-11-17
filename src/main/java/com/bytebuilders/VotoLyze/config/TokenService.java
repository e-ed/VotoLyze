/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bytebuilders.VotoLyze.config;

import com.auth0.jwt.algorithms.Algorithm;
import com.bytebuilders.VotoLyze.entidades.Eleitor;
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
            Algorithm algorithm = Algorithm.HMAC256();
            
        } catch() {
            
        }
        
    }
    
}
