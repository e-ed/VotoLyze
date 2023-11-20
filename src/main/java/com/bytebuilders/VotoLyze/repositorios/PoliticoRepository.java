package com.bytebuilders.VotoLyze.repositorios;

import com.bytebuilders.VotoLyze.entidades.Eleitor;
import com.bytebuilders.VotoLyze.entidades.Politico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliticoRepository extends JpaRepository<Politico, Integer> {
    public UserDetails findByEmail(String email);

    public Politico findByEmailIgnoreCase(String email);


}
