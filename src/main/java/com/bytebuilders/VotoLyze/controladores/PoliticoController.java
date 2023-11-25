package com.bytebuilders.VotoLyze.controladores;


import com.bytebuilders.VotoLyze.config.TokenService;
import com.bytebuilders.VotoLyze.entidades.*;
import com.bytebuilders.VotoLyze.servicos.PartidoServices;
import com.bytebuilders.VotoLyze.servicos.PoliticoServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/politico")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PoliticoController {
    @Autowired
    PoliticoServices politicoServices;

    @Autowired
    TokenService tokenService;


    @GetMapping("")
    public ResponseEntity<List<Politico>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(politicoServices.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id,
                                         @RequestBody @Valid PoliticoRegisterDTO registerDTO,
                                         @RequestHeader("Authorization") String authorization) {

        String extractedTokenFromHeader = tokenService.extractToken(authorization);
        String userRequesting = tokenService.getUserIdFromToken(extractedTokenFromHeader);
        Politico idFromUserRequesting = politicoServices.findByEmailIgnoreCase(userRequesting);

        if (!Objects.equals(idFromUserRequesting.getId(), id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ID mismatch for operation");
        }

        System.out.println(registerDTO);


        Optional<Politico> toBeUpdated = politicoServices.findById(id);
        if (toBeUpdated.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        var updatedUser = new Politico();

        updatedUser.setEmail(registerDTO.getLogin());
        updatedUser.setSenha(toBeUpdated.get().getSenha());
        updatedUser.setNome(registerDTO.getNome());
        updatedUser.setSexo(registerDTO.getSexo().charAt(0));
        updatedUser.setCPF(registerDTO.getCPF());
        updatedUser.setDataNascimento(Date.valueOf(registerDTO.getDataNascimento().toLocalDate().plusDays(1)));
        updatedUser.setId(toBeUpdated.get().getId());
        updatedUser.setInicioMandato(registerDTO.getInicioMandato());
        updatedUser.setPartido(registerDTO.getPartido());
        updatedUser.setTipoCandidatura(registerDTO.getTipoCandidatura().getValue());


        return ResponseEntity.status(HttpStatus.OK).body(politicoServices.save(updatedUser));

    }



    @GetMapping("/findById/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        Optional<Politico> politico = politicoServices.findById(id);
        if (!politico.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        return ResponseEntity.status(HttpStatus.OK).body(politico);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Object> findById(@PathVariable String email) {
        Optional<UserDetails> politico = politicoServices.findByEmail(email);
        if (!politico.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        return ResponseEntity.status(HttpStatus.OK).body(politico);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id, @RequestHeader("Authorization") String authorization) {

        String extractedTokenFromHeader = tokenService.extractToken(authorization);
        String userRequesting = tokenService.getUserIdFromToken(extractedTokenFromHeader);
        System.out.println(userRequesting);
        Politico idFromUserRequesting = politicoServices.findByEmailIgnoreCase(userRequesting);
        System.out.println("user requesting: " + idFromUserRequesting.getId());
        System.out.println("id from URL: " + id);

        if (!Objects.equals(idFromUserRequesting.getId(), id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ID mismatch for operation");
        }


        Optional<Politico> toBeDeleted = politicoServices.findById(id);
        if (!toBeDeleted.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        politicoServices.delete(toBeDeleted.get());
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }

    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<Object> updatePassword(@PathVariable Integer id,
                                                 @RequestBody @Valid PoliticoRegisterDTO registerDTO,
                                                 @RequestHeader("Authorization") String authorization) {
        String extractedTokenFromHeader = tokenService.extractToken(authorization);
        String userRequesting = tokenService.getUserIdFromToken(extractedTokenFromHeader);
        System.out.println(userRequesting);
        Politico idFromUserRequesting = politicoServices.findByEmailIgnoreCase(userRequesting);
        System.out.println("user requesting: " + idFromUserRequesting.getId());
        System.out.println("id from URL: " + id);

        if (!Objects.equals(idFromUserRequesting.getId(), id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ID mismatch for operation");
        }


        Optional<Politico> toBeUpdated = politicoServices.findById(id);
        if (toBeUpdated.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        var updatedUser = new Politico();
        updatedUser.setEmail(toBeUpdated.get().getEmail());

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.getPassword());
        updatedUser.setSenha(encryptedPassword);

        updatedUser.setNome(toBeUpdated.get().getNome());
        updatedUser.setSexo(toBeUpdated.get().getSexo());
        updatedUser.setCPF(toBeUpdated.get().getCPF());
        updatedUser.setDataNascimento(Date.valueOf(toBeUpdated.get().getDataNascimento().toLocalDate().plusDays(1)));
        updatedUser.setId(toBeUpdated.get().getId());
        updatedUser.setTipoCandidatura(toBeUpdated.get().getTipoCandidatura());
        updatedUser.setPartido(toBeUpdated.get().getPartido());
        updatedUser.setInicioMandato(toBeUpdated.get().getInicioMandato());

        return ResponseEntity.status(HttpStatus.OK).body(politicoServices.save(updatedUser));

    }
}
