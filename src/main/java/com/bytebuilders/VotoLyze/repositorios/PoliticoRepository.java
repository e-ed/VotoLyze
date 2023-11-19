package com.bytebuilders.VotoLyze.repositorios;

import com.bytebuilders.VotoLyze.entidades.Politico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliticoRepository extends JpaRepository<Politico, Integer> {
}
