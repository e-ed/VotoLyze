package com.bytebuilders.VotoLyze.repositorios;

import com.bytebuilders.VotoLyze.entidades.Politico;
import com.bytebuilders.VotoLyze.entidades.Promessa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromessaRepository extends JpaRepository<Promessa, Integer> {

    @Query("SELECT p FROM Promessa p WHERE p.politico.id = :politicoId")
    List<Promessa> findByPoliticoId(@Param("politicoId") Integer politicoId);


}
