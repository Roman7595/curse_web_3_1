package com.web.curse.services;

import com.web.curse.dtos.out.TargetFeeOutputDto;
import com.web.curse.dtos.save.TargetFeeSaveDto;

import java.util.List;

public interface TargetFeeDomainService {
    TargetFeeOutputDto save(TargetFeeSaveDto targetToSave);

    List<TargetFeeOutputDto> findAll();

    TargetFeeOutputDto findByPayment(long id);
}
