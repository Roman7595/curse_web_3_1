package com.web.curse.repositories.impl;


import com.web.curse.entities.MembershipFeePayment;
import com.web.curse.repositories.baseRepositories.GetRepository;
import com.web.curse.repositories.baseRepositories.SaveRepository;
import com.web.curse.repositories.baseRepositories.UpdateRepository;
import com.web.curse.repositories.interfaces.MembershipFeePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class MembershipFeePaymentRepositoryImpl implements MembershipFeePaymentRepository {
    @Autowired
    GetRepository<MembershipFeePayment> getRepository;

    @Autowired
    SaveRepository<MembershipFeePayment> saveRepository;

    @Autowired
    UpdateRepository<MembershipFeePayment> updateRepository;

    @Override
    public Optional<MembershipFeePayment> findById(long id) {
        return getRepository.findById(id, MembershipFeePayment.class);
    }

    @Override
    public List<MembershipFeePayment> findAll() {
        return getRepository.findAll(MembershipFeePayment.class);
    }

    @Override
    public MembershipFeePayment save(MembershipFeePayment membershipFeePayment) {
        return saveRepository.save(membershipFeePayment);
    }

    @Override
    public MembershipFeePayment update(MembershipFeePayment membershipFeePayment) {
        return updateRepository.update(membershipFeePayment);
    }
}
