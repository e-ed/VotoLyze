package com.bytebuilders.VotoLyze.controladores;


import com.bytebuilders.VotoLyze.config.TokenService;
import com.bytebuilders.VotoLyze.entidades.Eleitor;
import com.bytebuilders.VotoLyze.entidades.RegisterDTO;
import com.bytebuilders.VotoLyze.servicos.EleitoresService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/eleitor")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EleitorController {

    @Autowired
    EleitoresService eleitoresService;

    @Autowired
    TokenService tokenService;

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id,
                                         @RequestBody @Valid RegisterDTO registerDTO,
                                         @RequestHeader("Authorization") String authorization) {

        String extractedTokenFromHeader = tokenService.extractToken(authorization);
        String userRequesting = tokenService.getUserIdFromToken(extractedTokenFromHeader);
        Eleitor idFromUserRequesting = eleitoresService.findByEmailIgnoreCase(userRequesting);

        if (!Objects.equals(idFromUserRequesting.getId(), id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ID mismatch for operation");
        }


        Optional<Eleitor> toBeUpdated = eleitoresService.findById(id);
        if (toBeUpdated.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        var updatedUser = new Eleitor();

        updatedUser.setEmail(registerDTO.getLogin());
        updatedUser.setSenha(toBeUpdated.get().getSenha());
        updatedUser.setNome(registerDTO.getNome());
        updatedUser.setSexo(registerDTO.getSexo().charAt(0));
        updatedUser.setCPF(registerDTO.getCPF());
        updatedUser.setDataNascimento(Date.valueOf(registerDTO.getDataNascimento().toLocalDate().plusDays(1)));
        updatedUser.setId(toBeUpdated.get().getId());


        return ResponseEntity.status(HttpStatus.OK).body(eleitoresService.save(updatedUser));

    }

    @GetMapping
    public ResponseEntity<List<Eleitor>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(eleitoresService.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        Optional<Eleitor> eleitor = eleitoresService.findById(id);
        if (!eleitor.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        return ResponseEntity.status(HttpStatus.OK).body(eleitor);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Object> findById(@PathVariable String email) {
        Optional<UserDetails> eleitor = eleitoresService.findByEmail(email);
        if (!eleitor.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        return ResponseEntity.status(HttpStatus.OK).body(eleitor);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id, @RequestHeader("Authorization") String authorization) {

        String extractedTokenFromHeader = tokenService.extractToken(authorization);
        String userRequesting = tokenService.getUserIdFromToken(extractedTokenFromHeader);
        System.out.println(userRequesting);
        Eleitor idFromUserRequesting = eleitoresService.findByEmailIgnoreCase(userRequesting);
        System.out.println("user requesting: " + idFromUserRequesting.getId());
        System.out.println("id from URL: " + id);

        if (!Objects.equals(idFromUserRequesting.getId(), id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ID mismatch for operation");
        }


        Optional<Eleitor> toBeDeleted = eleitoresService.findById(id);
        if (!toBeDeleted.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        eleitoresService.delete(toBeDeleted.get());
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }

    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<Object> updatePassword(@PathVariable Integer id,
                                                 @RequestBody @Valid RegisterDTO registerDTO,
                                                 @RequestHeader("Authorization") String authorization) {
        String extractedTokenFromHeader = tokenService.extractToken(authorization);
        String userRequesting = tokenService.getUserIdFromToken(extractedTokenFromHeader);
        System.out.println(userRequesting);
        Eleitor idFromUserRequesting = eleitoresService.findByEmailIgnoreCase(userRequesting);
        System.out.println("user requesting: " + idFromUserRequesting.getId());
        System.out.println("id from URL: " + id);

        if (!Objects.equals(idFromUserRequesting.getId(), id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ID mismatch for operation");
        }


        Optional<Eleitor> toBeUpdated = eleitoresService.findById(id);
        if (toBeUpdated.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        var updatedUser = new Eleitor();
        updatedUser.setEmail(toBeUpdated.get().getEmail());

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.getPassword());
        updatedUser.setSenha(encryptedPassword);

        updatedUser.setNome(toBeUpdated.get().getNome());
        updatedUser.setSexo(toBeUpdated.get().getSexo());
        updatedUser.setCPF(toBeUpdated.get().getCPF());
        updatedUser.setDataNascimento(Date.valueOf(toBeUpdated.get().getDataNascimento().toLocalDate().plusDays(1)));
        updatedUser.setId(toBeUpdated.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(eleitoresService.save(updatedUser));

    }

}
