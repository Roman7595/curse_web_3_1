package com.web.curse.repositories.impl;


import com.web.curse.entities.*;
import com.web.curse.repositories.baseRepositories.GetRepository;
import com.web.curse.repositories.baseRepositories.SaveRepository;
import com.web.curse.repositories.baseRepositories.UpdateRepository;
import com.web.curse.repositories.customRepositories.MembershipFeePaymentCustomRepository;
import com.web.curse.repositories.interfaces.MembershipFeePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class MembershipFeePaymentRepositoryImpl implements MembershipFeePaymentRepository {
    @Autowired
    public MembershipFeePaymentRepositoryImpl(GetRepository<MembershipFeePayment> getRepository, SaveRepository<MembershipFeePayment> saveRepository, UpdateRepository<MembershipFeePayment> updateRepository, MembershipFeePaymentCustomRepository membershipFeePaymentCustomRepository) {
        this.getRepository = getRepository;
        this.saveRepository = saveRepository;
        this.updateRepository = updateRepository;
        this.membershipFeePaymentCustomRepository = membershipFeePaymentCustomRepository;
    }

    GetRepository<MembershipFeePayment> getRepository;
    SaveRepository<MembershipFeePayment> saveRepository;
    UpdateRepository<MembershipFeePayment> updateRepository;
    MembershipFeePaymentCustomRepository membershipFeePaymentCustomRepository;

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
    @Override
    public MembershipFeePayment findByLandAndMembershipFee(Land land, MembershipFee membershipFee) {
        return membershipFeePaymentCustomRepository.findByLandAndMembershipFee(land, membershipFee);
    }

    public List<MembershipFeePayment> findByLand(Land land){
        return membershipFeePaymentCustomRepository.findByLand(land);
    }

}
