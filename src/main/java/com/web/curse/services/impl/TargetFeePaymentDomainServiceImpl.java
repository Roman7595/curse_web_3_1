package com.web.curse.services.impl;

import com.web.curse.dtos.out.TargetFeePaymentOutputDto;
import com.web.curse.entities.Land;
import com.web.curse.entities.TargetFeePayment;
import com.web.curse.repositories.interfaces.*;
import com.web.curse.services.TargetFeePaymentDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TargetFeePaymentDomainServiceImpl implements TargetFeePaymentDomainService {
    @Autowired
    public TargetFeePaymentDomainServiceImpl(TargetFeePaymentRepository targetFeePaymentRepository, TargetFeeRepository targetFeeRepository, LandRepository landRepository) {
        this.targetFeePaymentRepository = targetFeePaymentRepository;
        this.targetFeeRepository = targetFeeRepository;
        this.landRepository = landRepository;
    }

    private TargetFeePaymentRepository targetFeePaymentRepository;
    private TargetFeeRepository targetFeeRepository;
    private LandRepository landRepository;


    public List<TargetFeePaymentOutputDto> findByLand(long landId){
        Optional<Land> land = landRepository.findById(landId);
        if (land.isEmpty()){
            throw new RuntimeException();//error: land not found
        }
        List<TargetFeePayment> targetFeePayments = targetFeePaymentRepository.findByLand(land.get());
        List<TargetFeePaymentOutputDto> results = new ArrayList<>();
        for (TargetFeePayment tp:targetFeePayments) {
            results.add(new TargetFeePaymentOutputDto(tp.getId(),tp.getFeeSum(),tp.getPaymentLocalDate()));
        }
        return results;
    }

    @Override
    public TargetFeePaymentOutputDto pay(long targetFeePaymentId) {
        Optional<TargetFeePayment> targetFeePayment = targetFeePaymentRepository.findById(targetFeePaymentId);
        if(targetFeePayment.isEmpty()){
            throw new RuntimeException();//error: not found
        }
        if (targetFeePayment.get().getPaymentLocalDate()!=null){
            throw new RuntimeException(); // already paid
        }

        targetFeePayment.get().setPaymentLocalDate(new Date());

        if (targetFeePaymentRepository.update(targetFeePayment.get())==null){
            throw new RuntimeException();//cant update
        }
        return new TargetFeePaymentOutputDto(targetFeePayment.get().getId(), targetFeePayment.get().getFeeSum(),targetFeePayment.get().getPaymentLocalDate());
    }



}
