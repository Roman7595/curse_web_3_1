package com.web.curse.dtos.account;

import com.web.curse.dtos.out.LandOutputDto;

import java.util.List;

public record FullLandOutputDto(
        LandOutputDto landOutputDto,
        List<FullTariffPaymentOutputDto> fullTariffPaymentOutputDtoList,
        List<FullTargetFeePaymentOutputDto> fullTargetFeePaymentOutputDtoList,
        List<FullMembershipFeePaymentOutputDto> fullMembershipFeePaymentOutputList
) {
}
