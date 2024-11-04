package com.web.curse.repositories.impl;


import com.web.curse.entities.TargetFeePayment;
import com.web.curse.repositories.baseRepositories.GetRepository;
import com.web.curse.repositories.baseRepositories.SaveRepository;
import com.web.curse.repositories.baseRepositories.UpdateRepository;
import com.web.curse.repositories.interfaces.TargetFeePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class TargetFeePaymentRepositoryImpl implements TargetFeePaymentRepository {
    @Autowired
    GetRepository<TargetFeePayment> getRepository;

    @Autowired
    SaveRepository<TargetFeePayment> saveRepository;

    @Autowired
    UpdateRepository<TargetFeePayment> updateRepository;


    @Override
    public Optional<TargetFeePayment> findById(long id) {
        return getRepository.findById(id,TargetFeePayment.class);
    }

    @Override
    public List<TargetFeePayment> findAll() {
        return getRepository.findAll(TargetFeePayment.class);
    }

    @Override
    public TargetFeePayment save(TargetFeePayment targetFeePayment) {
        return saveRepository.save(targetFeePayment);
    }

    @Override
    public TargetFeePayment update(TargetFeePayment targetFeePayment) {
        return updateRepository.update(targetFeePayment);
    }
}
