package com.web.curse.dtos.account;

import com.web.curse.dtos.out.MembershipFeeOutputDto;
import com.web.curse.dtos.out.MembershipFeePaymentOutputDto;

public record FullMembershipFeePaymentOutputDto(
        MembershipFeePaymentOutputDto membershipFeePaymentOutputDto,
        MembershipFeeOutputDto membershipFeeOutputDto

) {
}
