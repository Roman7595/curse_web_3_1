package com.web.curse.repositories.impl;


import com.web.curse.entities.MembershipFee;
import com.web.curse.repositories.baseRepositories.GetRepository;
import com.web.curse.repositories.baseRepositories.SaveRepository;
import com.web.curse.repositories.interfaces.MembershipFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class MembershipFeeRepositoryImpl implements MembershipFeeRepository {
    @Autowired
    GetRepository<MembershipFee> getRepository;

    @Autowired
    SaveRepository<MembershipFee> saveRepository;


    @Override
    public MembershipFee save(MembershipFee membershipFee) {
        return saveRepository.save(membershipFee);
    }


    @Override
    public List<MembershipFee> findAll() {
        return getRepository.findAll(MembershipFee.class);
    }

    @Override
    public Optional<MembershipFee> findById(long id){
        return getRepository.findById(id,MembershipFee.class);
    }

}
