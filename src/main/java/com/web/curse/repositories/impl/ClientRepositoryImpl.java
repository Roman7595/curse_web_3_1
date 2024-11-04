package com.web.curse.repositories.impl;


import com.web.curse.entities.Client;
import com.web.curse.entities.Land;
import com.web.curse.repositories.baseRepositories.GetRepository;
import com.web.curse.repositories.baseRepositories.SaveRepository;
//import com.web.curse.repositories.customRepositories.ClientCustomRepository;
import com.web.curse.repositories.customRepositories.ClientCustomRepository;
import com.web.curse.repositories.interfaces.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
class ClientRepositoryImpl implements ClientRepository {
    @Autowired
    GetRepository<Client> getRepository;

    @Autowired
    SaveRepository<Client> saveRepository;

    @Autowired
    ClientCustomRepository clientCustomRepository;


    @Override
    public Client save(Client client) {
        return saveRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return getRepository.findAll(Client.class);
    }

    @Override
    public Optional<Client> findById(long id){
        return getRepository.findById(id,Client.class);
    }

    @Override
    public Client getWhereLand(Land land) {
        return clientCustomRepository.getWhereLand(land);
    }

}
