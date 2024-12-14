package com.web.curse.services.impl;

import com.web.curse.entities.Client;
import com.web.curse.entities.Role;
import com.web.curse.repositories.interfaces.ClientRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class AppUserDetailsService implements UserDetailsService {
    private ClientRepository clientRepository;

    public AppUserDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Client loadClient = clientRepository.findByLogin(login);
        if(loadClient==null){
            throw new UsernameNotFoundException(login+" не найден");//error: not found
        }

        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        for (Role r:loadClient.getRoles()) {
            roles.add(new SimpleGrantedAuthority("ROLE_" + r.getName().name()));
        }

        return new User(loadClient.getLogin(),
                loadClient.getPassword(),
                roles
                 );

    }
}