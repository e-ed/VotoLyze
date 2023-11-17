
package com.bytebuilders.VotoLyze.controladores;

import com.bytebuilders.VotoLyze.entidades.AuthenticationDTO;
import com.bytebuilders.VotoLyze.entidades.Eleitor;
import com.bytebuilders.VotoLyze.entidades.RegisterDTO;
import com.bytebuilders.VotoLyze.repositorios.EleitoresRepository;
import jakarta.validation.Valid;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author eduardo
 */

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    EleitoresRepository eleitoresRepository;
    
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var userPassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(userPassword);
        
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (eleitoresRepository.findByEmail(registerDTO.login()) != null) return ResponseEntity.badRequest().build();
        
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        Eleitor eleitor = new Eleitor();
        eleitor.setEmail(registerDTO.login());
        eleitor.setSenha(encryptedPassword);
        eleitor.setNome("placeholder");
        eleitor.setSexo('m');
        eleitor.setCPF("00000000000");
        eleitor.setDataNascimento(Date.valueOf("1900-01-01"));
        
        eleitoresRepository.save(eleitor);
        
        return ResponseEntity.ok().build();
    }
    
}
