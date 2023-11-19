package com.bytebuilders.VotoLyze.servicos;

import com.bytebuilders.VotoLyze.entidades.Partido;
import com.bytebuilders.VotoLyze.repositorios.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidoServices {
    @Autowired
    PartidoRepository partidoRepository;
    public List<Partido> findAll() {
        return partidoRepository.findAll();
    }
}
