package com.web.curse.services.impl;

import com.web.curse.dtos.out.MembershipFeePaymentOutputDto;
import com.web.curse.entities.Land;
import com.web.curse.entities.MembershipFeePayment;
import com.web.curse.repositories.interfaces.LandRepository;
import com.web.curse.repositories.interfaces.MembershipFeePaymentRepository;
import com.web.curse.repositories.interfaces.MembershipFeeRepository;
import com.web.curse.services.MembershipFeePaymentDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class MembershipFeePaymentDomainServiceImpl implements MembershipFeePaymentDomainService {
    @Autowired
    public MembershipFeePaymentDomainServiceImpl(MembershipFeePaymentRepository membershipFeePaymentRepository, LandRepository landRepository) {
        this.membershipFeePaymentRepository = membershipFeePaymentRepository;
        this.landRepository = landRepository;
    }

    private MembershipFeePaymentRepository membershipFeePaymentRepository;

    private LandRepository landRepository;


    public List<MembershipFeePaymentOutputDto> findByLand(long landId){
        Optional<Land> land = landRepository.findById(landId);
        if (land.isEmpty()){
            throw new RuntimeException();//error: land not found
        }
        List<MembershipFeePayment> membershipFeePayments = membershipFeePaymentRepository.findByLand(land.get());
        List<MembershipFeePaymentOutputDto> results = new ArrayList<>();
        for (MembershipFeePayment tp:membershipFeePayments) {
            results.add(new MembershipFeePaymentOutputDto(tp.getId(),tp.getFeeSum(),tp.getPaymentLocalDate()));
        }
        return results;
    }

    @Override
    public MembershipFeePaymentOutputDto pay(long membershipFeePaymentId) {
        Optional<MembershipFeePayment> membershipFeePayment = membershipFeePaymentRepository.findById(membershipFeePaymentId);
        if(membershipFeePayment.isEmpty()){
            throw new RuntimeException();//error: not found
        }
        if (membershipFeePayment.get().getPaymentLocalDate()!=null){
            throw new RuntimeException(); // already paid
        }

        membershipFeePayment.get().setPaymentLocalDate(new Date());
        if (membershipFeePaymentRepository.update(membershipFeePayment.get())==null){
            throw new RuntimeException();//cant update
        }
        return new MembershipFeePaymentOutputDto(membershipFeePayment.get().getId(), membershipFeePayment.get().getFeeSum(),membershipFeePayment.get().getPaymentLocalDate());
    }



}
