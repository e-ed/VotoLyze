package com.bytebuilders.VotoLyze.repositorios;

import com.bytebuilders.VotoLyze.entidades.Eleitores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


@Repository
public interface EleitoresRepository extends JpaRepository<Eleitores, Integer> {
    public UserDetails findByUSR_EMAIL(String USREMAIL);
}
