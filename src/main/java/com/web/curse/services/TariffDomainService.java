package com.web.curse.services;

import com.web.curse.dtos.out.TariffOutputDto;
import com.web.curse.dtos.save.TariffSaveDto;

import java.util.List;

public interface TariffDomainService {
    TariffOutputDto save(TariffSaveDto tariff);

    List<TariffOutputDto> findAll();

    TariffOutputDto findByPayment(long id);

}
