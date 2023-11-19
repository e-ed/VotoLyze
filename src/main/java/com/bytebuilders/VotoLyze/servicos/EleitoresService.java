/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bytebuilders.VotoLyze.servicos;

import com.bytebuilders.VotoLyze.entidades.Eleitor;
import com.bytebuilders.VotoLyze.repositorios.EleitoresRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author eduardo
 */

@Service
public class EleitoresService {

    @Autowired
    EleitoresRepository eleitoresRepository;

    public List<Eleitor> findAll() {
        return eleitoresRepository.findAll();
    }

    public Optional<UserDetails> findByEmail(String email) {
        var e = eleitoresRepository.findByEmail(email);
        if (eleitoresRepository.findByEmail(email) != null) {
            return Optional.ofNullable(e);
        } else {
            return null;
        }
    }

    public Optional<Eleitor> findById(Integer id) {
        return eleitoresRepository.findById(id);
    }

    @Transactional
    public Eleitor save(Eleitor eleitor) {


        return eleitoresRepository.save(eleitor);
    }

    @Transactional
    public void delete(Eleitor eleitor) {
        eleitoresRepository.delete(eleitor);
    }

    public Eleitor findByEmailIgnoreCase(String email) {
        return eleitoresRepository.findByEmailIgnoreCase(email);
    }
}
