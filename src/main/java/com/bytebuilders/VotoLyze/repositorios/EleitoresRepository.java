package com.bytebuilders.VotoLyze.repositorios;

import com.bytebuilders.VotoLyze.entidades.Eleitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


@Repository
public interface EleitoresRepository extends JpaRepository<Eleitor, Integer> {
    public UserDetails findByEmail(String email);
    public Eleitor findByEmailIgnoreCase(String email);

}
