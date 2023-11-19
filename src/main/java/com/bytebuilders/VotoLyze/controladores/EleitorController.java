package com.bytebuilders.VotoLyze.controladores;


import com.bytebuilders.VotoLyze.entidades.Eleitor;
import com.bytebuilders.VotoLyze.entidades.RegisterDTO;
import com.bytebuilders.VotoLyze.servicos.EleitoresService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/eleitor")
public class EleitorController {

    @Autowired
    EleitoresService eleitoresService;

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody @Valid RegisterDTO registerDTO) {
        Optional<Eleitor> toBeUpdated = eleitoresService.findById(id);
        System.out.println(toBeUpdated);
        if (toBeUpdated.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        var updatedUser = new Eleitor();
        updatedUser.setEmail(registerDTO.login());
        updatedUser.setSenha(toBeUpdated.get().getSenha());
        updatedUser.setNome(registerDTO.nome());
        updatedUser.setSexo(registerDTO.sexo().charAt(0));
        updatedUser.setCPF(registerDTO.CPF());
        updatedUser.setDataNascimento(Date.valueOf(registerDTO.dataNascimento().toLocalDate().plusDays(1)));
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


    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        Optional<Eleitor> toBeDeleted = eleitoresService.findById(id);
        if (!toBeDeleted.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        eleitoresService.delete(toBeDeleted.get());
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }
}
