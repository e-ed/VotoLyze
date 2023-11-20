package com.bytebuilders.VotoLyze.servicos;

import com.bytebuilders.VotoLyze.repositorios.EleitoresRepository;
import com.bytebuilders.VotoLyze.repositorios.PoliticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutorizacaoService implements UserDetailsService {

    @Autowired
    EleitoresRepository eleitoresRepository;

    @Autowired
    PoliticoRepository politicoRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = eleitoresRepository.findByEmail(username);

        if (userDetails == null) {
            userDetails = politicoRepository.findByEmail(username);
        }

        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return userDetails;
    }
}


