package com.bytebuilders.VotoLyze.repositorios;

import com.bytebuilders.VotoLyze.entidades.Politico;
import com.bytebuilders.VotoLyze.entidades.Promessa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromessaRepository extends JpaRepository<Promessa, Integer> {
    public List<Politico> findByPolitico(Politico politico);
}
