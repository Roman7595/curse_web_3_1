package com.web.curse.dtos.save;

import java.util.Date;

public class TariffSaveDto {
    public double waterTariff;
    public double singleElectricalTariff;
    public double doubleElectricalTariffDay;
    public double doubleElectricalTariffNight;

    public TariffSaveDto(double waterTariff, double singleElectricalTariff, double doubleElectricalTariffDay, double doubleElectricalTariffNight) {
        this.waterTariff = waterTariff;
        this.singleElectricalTariff = singleElectricalTariff;
        this.doubleElectricalTariffDay = doubleElectricalTariffDay;
        this.doubleElectricalTariffNight = doubleElectricalTariffNight;
    }
}
