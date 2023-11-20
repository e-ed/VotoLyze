package com.bytebuilders.VotoLyze.servicos;

import com.bytebuilders.VotoLyze.entidades.Eleitor;
import com.bytebuilders.VotoLyze.entidades.Partido;
import com.bytebuilders.VotoLyze.entidades.Politico;
import com.bytebuilders.VotoLyze.repositorios.PartidoRepository;
import com.bytebuilders.VotoLyze.repositorios.PoliticoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliticoServices {
    @Autowired
    PoliticoRepository politicoRepository;
    public List<Politico> findAll() {
        return politicoRepository.findAll();
    }

    public Politico findByEmailIgnoreCase(String email) {
        return politicoRepository.findByEmailIgnoreCase(email);
    }

    public Optional<Politico> findById(Integer id) {
        return politicoRepository.findById(id);
    }

    @Transactional
    public Politico save(Politico politico) {


        return politicoRepository.save(politico);
    }

    public Optional<UserDetails> findByEmail(String email) {
        var e = politicoRepository.findByEmail(email);
        if (politicoRepository.findByEmail(email) != null) {
            return Optional.ofNullable(e);
        } else {
            return null;
        }
    }

    @Transactional
    public void delete(Politico politico) {
        politicoRepository.delete(politico);
    }


}
