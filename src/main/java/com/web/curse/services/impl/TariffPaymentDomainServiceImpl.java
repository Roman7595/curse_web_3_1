package com.web.curse.services.impl;

import com.web.curse.dtos.out.TariffPaymentOutputDto;
import com.web.curse.dtos.save.TariffPaymentSaveDto;
import com.web.curse.entities.Land;
import com.web.curse.entities.Tariff;
import com.web.curse.entities.TariffPayment;
import com.web.curse.repositories.interfaces.LandRepository;
import com.web.curse.repositories.interfaces.TariffPaymentRepository;
import com.web.curse.repositories.interfaces.TariffRepository;
import com.web.curse.services.TariffPaymentDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TariffPaymentDomainServiceImpl implements TariffPaymentDomainService {
    @Autowired
    public TariffPaymentDomainServiceImpl(TariffPaymentRepository tariffPaymentRepository, TariffRepository tariffRepository, LandRepository landRepository) {
        this.tariffPaymentRepository = tariffPaymentRepository;
        this.tariffRepository = tariffRepository;
        this.landRepository = landRepository;
    }


    private TariffPaymentRepository tariffPaymentRepository;
    private TariffRepository tariffRepository;
    private LandRepository landRepository;

    public TariffPaymentOutputDto findById(long id){
        Optional<TariffPayment> res = tariffPaymentRepository.findById(id);
        if(res.isEmpty()){
            throw new RuntimeException(); //error: tariff payment not found
        }
        return this.tariffToOutput(res.get());
    }

    public long saveMeterReadings(List<TariffPaymentSaveDto> readings){
        Tariff newestTariff = tariffRepository.findLast();
        if (newestTariff==null){
            throw new RuntimeException();//error: tariffs not found
        }
        List<TariffPaymentOutputDto> result = new ArrayList<>();
        for (TariffPaymentSaveDto tariffPaymentSaveDto:readings) {

            Optional<Land> currentLand = landRepository.findById(tariffPaymentSaveDto.landId);
            if (currentLand.isEmpty()) {
                throw new RuntimeException();//error: land not found
            }

            TariffPayment tp = new TariffPayment(tariffPaymentSaveDto.waterUsage, tariffPaymentSaveDto.singleElectricalUsage, tariffPaymentSaveDto.doubleElectricalDayUsage, tariffPaymentSaveDto.doubleElectricalDayUsage, newestTariff, currentLand.get());
            if (tariffPaymentRepository.save(tp) == null) {
                throw new RuntimeException(); //error: cant register
            }
            result.add(tariffToOutput(tp));
        }
        return(result.size());





    }


    public List<TariffPaymentOutputDto> findByLand(long landId){
        Optional<Land> land = landRepository.findById(landId);
        if (land.isEmpty()){
            throw new RuntimeException();//error: land not found
        }
        List<TariffPayment> tariffPayments = tariffPaymentRepository.findByLand(land.get());
        List<TariffPaymentOutputDto> results = new ArrayList<>();
        for (TariffPayment tp:tariffPayments) {
            results.add(this.tariffToOutput(tp));
        }
        return results;
    }


    public TariffPaymentOutputDto pay(long tariffPaymentId) {
        Optional<TariffPayment> tariffPayment = tariffPaymentRepository.findById(tariffPaymentId);
        if(tariffPayment.isEmpty()){
            throw new RuntimeException();//error: not found
        }
        if (tariffPayment.get().getPaymentLocalDate()!=null){
            throw new RuntimeException(); // already paid
        }

        tariffPayment.get().setPaymentLocalDate(new Date());

        if (tariffPaymentRepository.update(tariffPayment.get())==null){
            throw new RuntimeException();//cant update
        }
        return this.tariffToOutput(tariffPayment.get());
    }


    private TariffPaymentOutputDto tariffToOutput(TariffPayment tp){

        TariffPayment last = tariffPaymentRepository.findLast(tp.getLand(),tp.getStartLocalDate());
        if (last==null){
            last = new TariffPayment(0,0,0,0,tp.getTariff(),tp.getLand());
        }
        double waterSum = (tp.getWaterUsage() - last.getWaterUsage())*tp.getTariff().getWaterTariff();
        double singleElectricSum = 0;
        double daySum = 0;
        double nightSum = 0;
        switch (tp.getLand().getElectricMeter()){
            case SINGLE -> {
                singleElectricSum = (tp.getSingleElectricalUsage() - last.getSingleElectricalUsage()) * tp.getTariff().getSingleElectricalTariff();
                daySum = 0;
                nightSum = 0;
            }
            case DOUBLE -> {
                singleElectricSum=0;
                daySum = (tp.getDoubleElectricalDayUsage() - last.getDoubleElectricalDayUsage()) * tp.getTariff().getDoubleElectricalTariffDay();
                nightSum = (tp.getDoubleElectricalNightUsage() - last.getDoubleElectricalNightUsage()) * tp.getTariff().getDoubleElectricalTariffNight();
            }

        }
        return new TariffPaymentOutputDto(tp.getId(), tp.getWaterUsage(),tp.getSingleElectricalUsage(),tp.getDoubleElectricalDayUsage(),tp.getDoubleElectricalNightUsage(),waterSum,singleElectricSum,daySum,nightSum,tp.getPaymentLocalDate(),tp.getStartLocalDate(),tp.getEndLocalDate());

    }

}
