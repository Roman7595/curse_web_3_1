package com.web.curse.services;

import com.web.curse.dtos.out.MembershipFeeOutputDto;
import com.web.curse.dtos.out.TariffOutputDto;
import com.web.curse.dtos.save.MembershipFeeSaveDto;
import com.web.curse.dtos.save.TariffSaveDto;

import java.util.List;

public interface MembershipFeeDomainService {
    MembershipFeeOutputDto save(MembershipFeeSaveDto targetToSave);

    List<MembershipFeeOutputDto> findAll();

    MembershipFeeOutputDto findByPayment(long id);
}
