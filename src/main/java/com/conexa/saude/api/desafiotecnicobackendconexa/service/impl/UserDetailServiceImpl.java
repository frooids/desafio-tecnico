package com.conexa.saude.api.desafiotecnicobackendconexa.service.impl;

import com.conexa.saude.api.desafiotecnicobackendconexa.entities.Medico;
import com.conexa.saude.api.desafiotecnicobackendconexa.entities.security.UserSpringSecurity;
import com.conexa.saude.api.desafiotecnicobackendconexa.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    MedicoRepository medicoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Medico medico = medicoRepository.findByEmail(username);

        if (medico == null){
            throw new UsernameNotFoundException(String.format("Usuário não encontrado com o email: %s", username));
        }
        return new UserSpringSecurity(medico.getIdMedico(), medico.getEmail(), medico.getSenha(), medico.getPerfis());
    }
}
