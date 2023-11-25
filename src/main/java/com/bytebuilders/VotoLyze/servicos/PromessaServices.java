package com.bytebuilders.VotoLyze.servicos;

import com.bytebuilders.VotoLyze.entidades.Politico;
import com.bytebuilders.VotoLyze.repositorios.PromessaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromessaServices {
    @Autowired
    PromessaRepository promessaRepository;

    public List<Politico> findByPolitico(Politico politico) {
        return promessaRepository.findByPolitico(politico);
    }
}
