package com.bytebuilders.VotoLyze.controladores;


import com.bytebuilders.VotoLyze.entidades.Eleitor;
import com.bytebuilders.VotoLyze.servicos.EleitoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eleitor")
public class EleitorController {

    @Autowired
    EleitoresService eleitoresService;

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
