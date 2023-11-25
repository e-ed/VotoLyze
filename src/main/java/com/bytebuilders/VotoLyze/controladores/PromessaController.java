package com.bytebuilders.VotoLyze.controladores;


import com.bytebuilders.VotoLyze.entidades.Politico;
import com.bytebuilders.VotoLyze.entidades.Promessa;
import com.bytebuilders.VotoLyze.servicos.PoliticoServices;
import com.bytebuilders.VotoLyze.servicos.PromessaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/promessa")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PromessaController {

    @Autowired
    PromessaServices promessaServices;

    @Autowired
    PoliticoServices politicoServices;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByPolitico(@PathVariable Integer id) {
        Optional<Politico> politico = politicoServices.findById(id);


        if (!politico.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Politico nao encontrado!");

        return ResponseEntity.status(HttpStatus.OK).body(promessaServices.findByPoliticoId(id));
    }
}
