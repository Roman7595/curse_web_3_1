package com.web.curse.services.impl;

import com.web.curse.dtos.out.MembershipFeeOutputDto;
import com.web.curse.dtos.out.TargetFeeOutputDto;
import com.web.curse.dtos.save.MembershipFeeSaveDto;
import com.web.curse.entities.*;
import com.web.curse.repositories.interfaces.LandRepository;
import com.web.curse.repositories.interfaces.MembershipFeePaymentRepository;
import com.web.curse.repositories.interfaces.MembershipFeeRepository;
import com.web.curse.services.MembershipFeeDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@EnableCaching
public class MembershipFeeDomainServiceImpl implements MembershipFeeDomainService {

    @Autowired
    public MembershipFeeDomainServiceImpl(MembershipFeeRepository membershipFeeRepository, LandRepository landRepository, MembershipFeePaymentRepository membershipFeePaymentRepository) {
        this.membershipFeeRepository = membershipFeeRepository;
        this.landRepository = landRepository;
        this.membershipFeePaymentRepository = membershipFeePaymentRepository;
    }

    private MembershipFeeRepository membershipFeeRepository;

    private LandRepository landRepository;

    private MembershipFeePaymentRepository membershipFeePaymentRepository;

    @CacheEvict(cacheNames = "memberships",allEntries = true)
    public MembershipFeeOutputDto save(MembershipFeeSaveDto targetToSave){
        MembershipFee membershipFee = new MembershipFee(targetToSave.contributionAmount);
        if (membershipFeeRepository.save(membershipFee)==null){
            throw new RuntimeException(); // error: cant register
        }

        List<Land> allLands = landRepository.findAll();
        long landsCount = allLands.size();
        for (Land land:allLands) {
            if (membershipFeePaymentRepository.findByLandAndMembershipFee(land,membershipFee)!=null){
                throw new RuntimeException(); //land.id already have to pay
            }

            MembershipFeePayment curPayment = new MembershipFeePayment(targetToSave.contributionAmount/landsCount,membershipFee,land);
            if(membershipFeePaymentRepository.save(curPayment)==null){
                throw new RuntimeException();//cant add payment to land.id
            }

        }
        return new MembershipFeeOutputDto(membershipFee.getId(),membershipFee.getContributionAmount(),membershipFee.getStartLocalDate(),membershipFee.getEndLocalDate());
    }

    @Cacheable("memberships")
    public List<MembershipFeeOutputDto> findAll(){
        List<MembershipFeeOutputDto> result = new ArrayList<>();
        for (MembershipFee m:membershipFeeRepository.findAll()) {
            result.add(new MembershipFeeOutputDto(m.getId(),m.getContributionAmount(),m.getStartLocalDate(),m.getEndLocalDate()));
        }
        return result;
    }

    public MembershipFeeOutputDto findByPayment(long id){
        Optional<MembershipFeePayment> membershipFeePayment = membershipFeePaymentRepository.findById(id);
        if (membershipFeePayment.isEmpty()){
            throw new RuntimeException();//error: land not found
        }
        MembershipFee membershipFee = membershipFeeRepository.findByPayment(membershipFeePayment.get());

        return(new MembershipFeeOutputDto(membershipFee.getId(),membershipFee.getContributionAmount(),membershipFee.getStartLocalDate(),membershipFee.getEndLocalDate()));

    }

}
