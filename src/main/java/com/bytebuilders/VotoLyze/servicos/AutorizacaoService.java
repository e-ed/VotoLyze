package com.bytebuilders.VotoLyze.servicos;

import com.bytebuilders.VotoLyze.repositorios.EleitoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutorizacaoService implements UserDetailsService {

    @Autowired
    EleitoresRepository eleitoresRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return eleitoresRepository.findByEmail(username);
    }
}
