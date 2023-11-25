package com.bytebuilders.VotoLyze.controladores;

import com.bytebuilders.VotoLyze.config.TokenService;
import com.bytebuilders.VotoLyze.entidades.*;
import com.bytebuilders.VotoLyze.repositorios.EleitoresRepository;
import com.bytebuilders.VotoLyze.repositorios.PartidoRepository;
import com.bytebuilders.VotoLyze.repositorios.PoliticoRepository;
import jakarta.validation.Valid;
import java.sql.Date;
import java.util.Optional;

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

    @Autowired
    PartidoRepository partidoRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var userPassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
        var auth = this.authenticationManager.authenticate(userPassword);

        var userLogin = eleitoresRepository.findByEmailIgnoreCase(data.getLogin());
        System.out.println(userLogin);

        String token;
        String tipoUsuario;



        if (userLogin != null) {
            token = tokenService.generateToken((Eleitor) auth.getPrincipal());
            tipoUsuario = "eleitor";
        }
        else {
            token = tokenService.politicoGenerateToken((Politico) auth.getPrincipal());
            tipoUsuario = "politico";
        }

        var loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken(token);
        loginResponseDTO.setTipoUsuario(tipoUsuario);


        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {
        System.out.println(registerDTO);

        if (eleitoresRepository.findByEmail(registerDTO.getLogin()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.getPassword());
        Eleitor eleitor = new Eleitor();
        eleitor.setEmail(registerDTO.getLogin());
        eleitor.setSenha(encryptedPassword);
        eleitor.setNome(registerDTO.getNome());
        eleitor.setSexo(registerDTO.getSexo().charAt(0));
        eleitor.setCPF(registerDTO.getCPF());
        eleitor.setDataNascimento(Date.valueOf(registerDTO.getDataNascimento().toLocalDate().plusDays(1)));


        return ResponseEntity.status(HttpStatus.CREATED).body( eleitoresRepository.save(eleitor));
    }

    @PostMapping("/politico/register")
    public ResponseEntity politicianRegister(@RequestBody @Valid PoliticoRegisterDTO politicoRegisterDTO) {


        if (politicoRepository.findByEmail(politicoRegisterDTO.getLogin()) != null) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("already registered!");
        }

        System.out.println("--dados--");
        System.out.println(politicoRegisterDTO.getLogin());
        System.out.println(politicoRegisterDTO.getPassword());
        System.out.println(politicoRegisterDTO.getCPF());
        System.out.println(politicoRegisterDTO.getSexo());


        String encryptedPassword = new BCryptPasswordEncoder().encode(politicoRegisterDTO.getPassword());
        Politico politico = new Politico();
        politico.setEmail(politicoRegisterDTO.getLogin());
        politico.setSenha(encryptedPassword);
        politico.setNome(politicoRegisterDTO.getNome());
        politico.setSexo(politicoRegisterDTO.getSexo().charAt(0));
        politico.setCPF(politicoRegisterDTO.getCPF());
        politico.setDataNascimento(Date.valueOf(politicoRegisterDTO.getDataNascimento().toLocalDate().plusDays(1)));
        politico.setInicioMandato(politicoRegisterDTO.getInicioMandato());



        switch (politicoRegisterDTO.getTipoCandidatura()) {
            case VEREADOR:
                politico.setTipoCandidatura(TipoCandidatura.VEREADOR.getValue());

                break;
            case PREFEITO:
                politico.setTipoCandidatura(TipoCandidatura.PREFEITO.getValue());
                break;
        }


        System.out.println("Partido: " + politicoRegisterDTO.getPartido());
        Optional<Partido> partido = partidoRepository.findById(politicoRegisterDTO.getPartido().getId());

        politico.setPartido(partido.get());

        System.out.println(politico.getPartido().getId());
        System.out.println(politico.getPartido().getNome());
        System.out.println(politico.getPartido().getSigla());


        return ResponseEntity.status(HttpStatus.CREATED).body( politicoRepository.save(politico));
    }

}
