package com.web.curse.services;

import com.web.curse.dtos.out.TariffPaymentOutputDto;
import com.web.curse.dtos.save.TariffPaymentSaveDto;

import java.util.List;

public interface TariffPaymentDomainService {

    TariffPaymentOutputDto findById(long id);
    long saveMeterReadings(List<TariffPaymentSaveDto> readings);

    List<TariffPaymentOutputDto> findByLand(long landId);

    TariffPaymentOutputDto pay(long tariffPaymentId);



}
