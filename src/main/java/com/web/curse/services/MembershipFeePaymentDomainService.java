package com.web.curse.services;

import com.web.curse.dtos.out.MembershipFeePaymentOutputDto;
import com.web.curse.dtos.out.TargetFeePaymentOutputDto;

import java.util.List;

public interface MembershipFeePaymentDomainService {

    List<MembershipFeePaymentOutputDto> findByLand(long landId);

    MembershipFeePaymentOutputDto pay(long membershipFeePaymentId);

}
