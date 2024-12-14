package com.web.curse.repositories.impl;


import com.web.curse.entities.TargetFee;
import com.web.curse.entities.TargetFeePayment;
import com.web.curse.repositories.baseRepositories.GetRepository;
import com.web.curse.repositories.baseRepositories.SaveRepository;
import com.web.curse.repositories.customRepositories.TargetFeeCustomRepository;
import com.web.curse.repositories.interfaces.TargetFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class TargetFeeRepositoryImpl implements TargetFeeRepository {
    @Autowired
    public TargetFeeRepositoryImpl(GetRepository<TargetFee> getRepository, SaveRepository<TargetFee> saveRepository, TargetFeeCustomRepository targetFeeCustomRepository) {
        this.getRepository = getRepository;
        this.saveRepository = saveRepository;
        this.targetFeeCustomRepository = targetFeeCustomRepository;
    }


    GetRepository<TargetFee> getRepository;
    SaveRepository<TargetFee> saveRepository;

    TargetFeeCustomRepository targetFeeCustomRepository;
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


    public TargetFee findByPayment(TargetFeePayment targetFeePayment){
        return targetFeeCustomRepository.findByPayment(targetFeePayment);
    }
}
