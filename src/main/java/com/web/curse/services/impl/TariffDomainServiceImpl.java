package com.web.curse.services.impl;

import com.web.curse.dtos.out.TariffOutputDto;
import com.web.curse.dtos.save.TariffSaveDto;
import com.web.curse.entities.*;
import com.web.curse.repositories.interfaces.TariffPaymentRepository;
import com.web.curse.repositories.interfaces.TariffRepository;
import com.web.curse.services.TariffDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@EnableCaching
public class TariffDomainServiceImpl implements TariffDomainService {
    private TariffRepository tariffRepository;

    private TariffPaymentRepository tariffPaymentRepository;

    @Autowired
    public void setTariffPaymentRepository(TariffPaymentRepository tariffPaymentRepository) {
        this.tariffPaymentRepository = tariffPaymentRepository;
    }

    @Autowired
    public void setTariffRepository(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @CacheEvict(cacheNames = "tariffs",allEntries = true)
    public TariffOutputDto save(TariffSaveDto tariffToSave) {
        Tariff tariff = new Tariff(tariffToSave.waterTariff,tariffToSave.singleElectricalTariff,tariffToSave.doubleElectricalTariffDay,tariffToSave.doubleElectricalTariffNight);
        if(tariffRepository.save(tariff)==null){
            throw new RuntimeException();//not saved
        }

        return new TariffOutputDto(tariff.getId(),tariff.getWaterTariff(),tariff.getSingleElectricalTariff(),tariff.getDoubleElectricalTariffDay(),tariff.getDoubleElectricalTariffNight(),tariff.getStartLocalDate());
    }
    @Cacheable("tariffs")
    public List<TariffOutputDto> findAll(){
        List<TariffOutputDto> result = new ArrayList<>();
        for (Tariff t:tariffRepository.findAll()) {
            result.add(new TariffOutputDto(t.getId(), t.getWaterTariff(), t.getSingleElectricalTariff(), t.getDoubleElectricalTariffDay(), t.getDoubleElectricalTariffNight(),t.getStartLocalDate()));
        }
        return result;
    }

    public TariffOutputDto findByPayment(long id){
            Optional<TariffPayment> tariffPayment = tariffPaymentRepository.findById(id);
            if (tariffPayment.isEmpty()){
                throw new RuntimeException();//error: land not found
            }
            Tariff tariff = tariffRepository.findByPayment(tariffPayment.get());

            return(new TariffOutputDto(tariff.getId(),tariff.getWaterTariff(),tariff.getSingleElectricalTariff(),tariff.getDoubleElectricalTariffDay(),tariff.getDoubleElectricalTariffNight(),tariff.getStartLocalDate()));


    }

}
