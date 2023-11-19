package com.bytebuilders.VotoLyze.controladores;

import com.bytebuilders.VotoLyze.config.TokenService;
import com.bytebuilders.VotoLyze.entidades.*;
import com.bytebuilders.VotoLyze.repositorios.EleitoresRepository;
import com.bytebuilders.VotoLyze.repositorios.PoliticoRepository;
import jakarta.validation.Valid;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController {

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EleitoresRepository eleitoresRepository;

    @Autowired
    PoliticoRepository politicoRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var userPassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(userPassword);

        var token = tokenService.generateToken((Eleitor) auth.getPrincipal());


        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {
        System.out.println(registerDTO);

        if (eleitoresRepository.findByEmail(registerDTO.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        Eleitor eleitor = new Eleitor();
        eleitor.setEmail(registerDTO.login());
        eleitor.setSenha(encryptedPassword);
        eleitor.setNome(registerDTO.nome());
        eleitor.setSexo(registerDTO.sexo().charAt(0));
        eleitor.setCPF(registerDTO.CPF());
        eleitor.setDataNascimento(Date.valueOf(registerDTO.dataNascimento().toLocalDate().plusDays(1)));


        return ResponseEntity.status(HttpStatus.CREATED).body( eleitoresRepository.save(eleitor));
    }

    @PostMapping("/politico/register")
    public ResponseEntity politicianRegister(@RequestBody @Valid PoliticoRegisterDTO politicoRegisterDTO) {


        if (politicoRepository.findByEmail(politicoRegisterDTO.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(politicoRegisterDTO.password());
        Politico politico = new Politico();
        politico.setEmail(politicoRegisterDTO.login());
        politico.setSenha(encryptedPassword);
        politico.setNome(politicoRegisterDTO.nome());
        politico.setSexo(politicoRegisterDTO.sexo().charAt(0));
        politico.setCPF(politicoRegisterDTO.CPF());
        politico.setDataNascimento(Date.valueOf(politicoRegisterDTO.dataNascimento().toLocalDate().plusDays(1)));
        politico.setInicioMandato(politicoRegisterDTO.inicioMandato());
        politico.setTipoCandidatura(politicoRegisterDTO.tipoCandidatura());


        return ResponseEntity.status(HttpStatus.CREATED).body( politicoRepository.save(politico));
    }

}
