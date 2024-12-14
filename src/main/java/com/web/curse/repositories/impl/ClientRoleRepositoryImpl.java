package com.web.curse.repositories.impl;

import com.web.curse.entities.Role;
import com.web.curse.entities.enums.ClientRoles;
import com.web.curse.repositories.baseRepositories.GetRepository;
import com.web.curse.repositories.baseRepositories.SaveRepository;
import com.web.curse.repositories.customRepositories.ClientRoleCustomRepository;
import com.web.curse.repositories.interfaces.ClientRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class ClientRoleRepositoryImpl implements ClientRoleRepository {
    @Autowired
    public ClientRoleRepositoryImpl(GetRepository<Role> getRepository, SaveRepository<Role> saveRepository, ClientRoleCustomRepository clientRoleCustomRepository) {
        this.getRepository = getRepository;
        this.saveRepository = saveRepository;
        this.clientRoleCustomRepository = clientRoleCustomRepository;
    }

    GetRepository<Role> getRepository;

    SaveRepository<Role> saveRepository;

    ClientRoleCustomRepository clientRoleCustomRepository;


    @Override
    public Role save(Role role) {
        return saveRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return getRepository.findAll(Role.class);
    }

    @Override
    public Role findByName(ClientRoles name){
        return clientRoleCustomRepository.findByName(name);
    }

}
