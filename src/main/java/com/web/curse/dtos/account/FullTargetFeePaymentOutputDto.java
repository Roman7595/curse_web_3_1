package com.web.curse.dtos.account;

import com.web.curse.dtos.out.TargetFeeOutputDto;
import com.web.curse.dtos.out.TargetFeePaymentOutputDto;
import com.web.curse.dtos.out.TariffOutputDto;
import com.web.curse.dtos.out.TariffPaymentOutputDto;

public record FullTargetFeePaymentOutputDto(
        TargetFeePaymentOutputDto targetFeePaymentOutputDto,
        TargetFeeOutputDto targetFeeOutputDto
) {
}
