package com.bytebuilders.VotoLyze.repositorios;

import com.bytebuilders.VotoLyze.entidades.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {

}
