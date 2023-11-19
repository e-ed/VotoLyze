package com.bytebuilders.VotoLyze.servicos;

import com.bytebuilders.VotoLyze.entidades.Partido;
import com.bytebuilders.VotoLyze.entidades.Politico;
import com.bytebuilders.VotoLyze.repositorios.PartidoRepository;
import com.bytebuilders.VotoLyze.repositorios.PoliticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliticoServices {
    @Autowired
    PoliticoRepository politicoRepository;
    public List<Politico> findAll() {
        return politicoRepository.findAll();
    }
}
