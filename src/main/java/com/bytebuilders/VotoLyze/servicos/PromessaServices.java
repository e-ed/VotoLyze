package com.bytebuilders.VotoLyze.servicos;

import com.bytebuilders.VotoLyze.entidades.Politico;
import com.bytebuilders.VotoLyze.entidades.Promessa;
import com.bytebuilders.VotoLyze.repositorios.PromessaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromessaServices {
    @Autowired
    PromessaRepository promessaRepository;

    public List<Promessa> findByPoliticoId(Integer id) {
        return promessaRepository.findByPoliticoId(id);
    }

    public Promessa save(Promessa promessa) {
        return promessaRepository.save(promessa);
    }
}
