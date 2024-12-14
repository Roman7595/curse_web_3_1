package com.web.curse.controllers;

import com.web.curse.dtos.save.TariffPaymentSaveDto;
import com.web.curse.services.TariffPaymentDomainService;
import org.example.controllers.ReadingsController;
import org.example.viewmodel.forms.TariffPaymentSaveForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/readings")
public class ReadingsControllerImpl implements ReadingsController {
    TariffPaymentDomainService tariffPaymentDomainService;

    @Autowired
    public ReadingsControllerImpl(TariffPaymentDomainService tariffPaymentDomainService) {
        this.tariffPaymentDomainService = tariffPaymentDomainService;
    }
    @Override
    public long saveReadings(List<TariffPaymentSaveForm> tariffPaymentSaveForms) {
        List<TariffPaymentSaveDto> tariffs = new ArrayList<>();
        for (TariffPaymentSaveForm t:tariffPaymentSaveForms) {
            tariffs.add(new TariffPaymentSaveDto(t.waterUsage(),t.singleElectricalUsage(),t.doubleElectricalDayUsage(),t.doubleElectricalNightUsage(),t.landId()));
        }
        return tariffPaymentDomainService.saveMeterReadings(tariffs);
    }
}
