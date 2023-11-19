package com.bytebuilders.VotoLyze.controladores;


import com.bytebuilders.VotoLyze.entidades.Partido;
import com.bytebuilders.VotoLyze.servicos.PartidoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/partido")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PartidoController {
    @Autowired
    PartidoServices partidoServices;

    @GetMapping("")
    public ResponseEntity<List<Partido>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(partidoServices.findAll());
    }
}
