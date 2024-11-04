package com.web.curse.repositories.impl;


import com.web.curse.entities.Tariff;
import com.web.curse.repositories.baseRepositories.GetRepository;
import com.web.curse.repositories.baseRepositories.SaveRepository;
import com.web.curse.repositories.baseRepositories.UpdateRepository;
import com.web.curse.repositories.interfaces.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class TariffRepositoryImpl implements TariffRepository {
    @Autowired
    GetRepository<Tariff> getRepository;

    @Autowired
    SaveRepository<Tariff> saveRepository;

    @Autowired
    UpdateRepository<Tariff> updateRepository;


    @Override
    public Tariff save(Tariff tariff) {
        return saveRepository.save(tariff);
    }


    @Override
    public List<Tariff> findAll() {
        return getRepository.findAll(Tariff.class);
    }

    @Override
    public Optional<Tariff> findById(long id){
        return getRepository.findById(id,Tariff.class);
    }

}
