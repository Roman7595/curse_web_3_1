package com.web.curse.services;

import com.web.curse.dtos.out.TargetFeePaymentOutputDto;

import java.util.List;

public interface TargetFeePaymentDomainService {

    List<TargetFeePaymentOutputDto> findByLand(long landId);

    TargetFeePaymentOutputDto pay(long targetFeePaymentId);

}
