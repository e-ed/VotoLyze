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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/eleitor")
public class EleitorController {

    @Autowired
    EleitoresService eleitoresService;

    @PutMapping("{email}")
    public ResponseEntity<Object> update(@PathVariable String email, @RequestBody @Valid RegisterDTO registerDTO) {
        Eleitor toBeUpdated = eleitoresService.findByEmail(email, 0);
        if (toBeUpdated == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        var updatedUser = new Eleitor();
        BeanUtils.copyProperties(registerDTO, updatedUser);
        updatedUser.setId(toBeUpdated.getId());
        return ResponseEntity.status(HttpStatus.OK).body( eleitoresService.save(updatedUser) );

    }

    @GetMapping
    public ResponseEntity<List<Eleitor>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(eleitoresService.findAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Object> findById(@PathVariable String email) {
        Optional<UserDetails> eleitor = eleitoresService.findByEmail(email);
        if (!eleitor.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        return ResponseEntity.status(HttpStatus.OK).body(eleitor);
    }
}
