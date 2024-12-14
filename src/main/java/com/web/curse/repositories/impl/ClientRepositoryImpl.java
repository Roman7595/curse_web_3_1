package com.web.curse.repositories.impl;


import com.web.curse.entities.Client;
import com.web.curse.entities.Land;
import com.web.curse.repositories.baseRepositories.GetRepository;
import com.web.curse.repositories.baseRepositories.SaveRepository;
import com.web.curse.repositories.customRepositories.ClientCustomRepository;
import com.web.curse.repositories.interfaces.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
class ClientRepositoryImpl implements ClientRepository {
    @Autowired
    public void setGetRepository(GetRepository<Client> getRepository) {
        this.getRepository = getRepository;
    }

    @Autowired
    public void setSaveRepository(SaveRepository<Client> saveRepository) {
        this.saveRepository = saveRepository;
    }
    @Autowired
    public void setClientCustomRepository(ClientCustomRepository clientCustomRepository) {
        this.clientCustomRepository = clientCustomRepository;
    }

    GetRepository<Client> getRepository;

    SaveRepository<Client> saveRepository;

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
    public Client findByLand(Land land) {
        return clientCustomRepository.findByLand(land);
    }

    @Override
    public Client findByLogin(String login) {
        return clientCustomRepository.findByLogin(login);
    }

}
