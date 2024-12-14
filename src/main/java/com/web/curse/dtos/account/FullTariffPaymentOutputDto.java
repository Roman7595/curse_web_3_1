package com.web.curse.dtos.account;

import com.web.curse.dtos.out.TariffOutputDto;
import com.web.curse.dtos.out.TariffPaymentOutputDto;

public record FullTariffPaymentOutputDto(
        TariffPaymentOutputDto tariffPaymentOutputDto,
        TariffOutputDto tariffOutputDto
) {
}
