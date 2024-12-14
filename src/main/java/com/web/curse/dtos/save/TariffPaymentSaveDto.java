package com.web.curse.dtos.save;

import com.web.curse.entities.Land;
import com.web.curse.entities.Tariff;

import java.util.Date;

public class TariffPaymentSaveDto {
    public double waterUsage;
    public double singleElectricalUsage;
    public double doubleElectricalDayUsage;
    public double doubleElectricalNightUsage;
    public long landId;

    public TariffPaymentSaveDto(double waterUsage, double singleElectricalUsage, double doubleElectricalDayUsage, double doubleElectricalNightUsage, long landId) {
        this.waterUsage = waterUsage;
        this.singleElectricalUsage = singleElectricalUsage;
        this.doubleElectricalDayUsage = doubleElectricalDayUsage;
        this.doubleElectricalNightUsage = doubleElectricalNightUsage;
        this.landId = landId;
    }
}
