package com.web.curse.repositories.impl;


import com.web.curse.entities.Land;
import com.web.curse.repositories.baseRepositories.GetRepository;
import com.web.curse.repositories.baseRepositories.SaveRepository;
import com.web.curse.repositories.baseRepositories.UpdateRepository;
import com.web.curse.repositories.interfaces.LandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class LandRepositoryImpl implements LandRepository {
    @Autowired
    GetRepository<Land> getRepository;

    @Autowired
    SaveRepository<Land> saveRepository;

    @Autowired
    UpdateRepository<Land> updateRepository;


    @Override
    public Land save(Land land) {
        return saveRepository.save(land);
    }

    @Override
    public Land update(Land land) {
        return updateRepository.update(land);
    }

    @Override
    public List<Land> findAll() {
        return getRepository.findAll(Land.class);
    }

    @Override
    public Optional<Land> findById(long id){
        return getRepository.findById(id,Land.class);
    }

}
