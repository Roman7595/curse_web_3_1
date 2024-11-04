package com.web.curse.repositories.impl;


import com.web.curse.entities.TargetFee;
import com.web.curse.repositories.baseRepositories.GetRepository;
import com.web.curse.repositories.baseRepositories.SaveRepository;
import com.web.curse.repositories.interfaces.TargetFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class TargetFeeRepositoryImpl implements TargetFeeRepository {
    @Autowired
    GetRepository<TargetFee> getRepository;
    @Autowired
    SaveRepository<TargetFee> saveRepository;
    @Override
    public TargetFee save(TargetFee targetFee) {
        return saveRepository.save(targetFee);
    }


    @Override
    public List<TargetFee> findAll() {
        return getRepository.findAll(TargetFee.class);
    }

    @Override
    public Optional<TargetFee> findById(long id){
        return getRepository.findById(id,TargetFee.class);
    }

}
