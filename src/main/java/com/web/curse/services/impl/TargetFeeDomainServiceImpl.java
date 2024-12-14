package com.web.curse.services.impl;

import com.web.curse.dtos.out.TargetFeeOutputDto;
import com.web.curse.dtos.save.TargetFeeSaveDto;
import com.web.curse.entities.*;
import com.web.curse.repositories.interfaces.LandRepository;
import com.web.curse.repositories.interfaces.TargetFeePaymentRepository;
import com.web.curse.repositories.interfaces.TargetFeeRepository;
import com.web.curse.services.TargetFeeDomainService;
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
public class TargetFeeDomainServiceImpl implements TargetFeeDomainService{

    @Autowired
    public TargetFeeDomainServiceImpl(TargetFeeRepository targetFeeRepository, LandRepository landRepository, TargetFeePaymentRepository targetFeePaymentRepository) {
        this.targetFeeRepository = targetFeeRepository;
        this.landRepository = landRepository;
        this.targetFeePaymentRepository = targetFeePaymentRepository;
    }

    private TargetFeeRepository targetFeeRepository;

    private LandRepository landRepository;

    private TargetFeePaymentRepository targetFeePaymentRepository;

    @CacheEvict(cacheNames = "targets",allEntries = true)
    public TargetFeeOutputDto save(TargetFeeSaveDto targetToSave){
        TargetFee targetFee = new TargetFee(targetToSave.targetName,targetToSave.contributionAmount,targetToSave.endLocalDate);
        if (targetFeeRepository.save(targetFee)==null){
            throw new RuntimeException(); // error: cant register
        }

        List<Land> allLands = landRepository.findAll();
        long arsCount = allLands.stream().map(Land::getSizeInArs).reduce(0,Integer::sum);
        for (Land land:allLands) {
            if (targetFeePaymentRepository.findByLandAndTargetFee(land,targetFee)!=null){
                throw new RuntimeException(); //land.id already have to pay
            }

            TargetFeePayment curPayment = new TargetFeePayment(targetToSave.contributionAmount*land.getSizeInArs()/arsCount,targetFee,land);
            if(targetFeePaymentRepository.save(curPayment)==null){
                throw new RuntimeException();//cant add payment to land.id
            }

        }
        return new TargetFeeOutputDto(targetFee.getId(),targetFee.getTargetName(),targetFee.getContributionAmount(),targetFee.getStartLocalDate(),targetFee.getEndLocalDate());
    }

    @Cacheable("targets")
    public List<TargetFeeOutputDto> findAll(){
        List<TargetFeeOutputDto> result = new ArrayList<>();
        for (TargetFee t:targetFeeRepository.findAll()) {
            result.add(new TargetFeeOutputDto(t.getId(),t.getTargetName(),t.getContributionAmount(),t.getStartLocalDate(),t.getEndLocalDate()));
        }
        return result;
    }

    public TargetFeeOutputDto findByPayment(long id){
        Optional<TargetFeePayment> tariffPayment = targetFeePaymentRepository.findById(id);
        if (tariffPayment.isEmpty()){
            throw new RuntimeException();//error: land not found
        }
        TargetFee targetFee = targetFeeRepository.findByPayment(tariffPayment.get());

        return(new TargetFeeOutputDto(targetFee.getId(),targetFee.getTargetName(),targetFee.getContributionAmount(),targetFee.getStartLocalDate(),targetFee.getEndLocalDate()));

    }
}
