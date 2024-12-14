package com.web.curse.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tariffs")
public class Tariff extends BaseEntity{
    private double waterTariff;
    private double singleElectricalTariff;
    private double doubleElectricalTariffDay;
    private double doubleElectricalTariffNight;
    private Date startLocalDate;
    private Set<TariffPayment> tariffPayments;
    public Tariff(double waterTariff, double singleElectricalTariff, double doubleElectricalTariffDay, double doubleElectricalTariffNight) {
        this.waterTariff = waterTariff;
        this.singleElectricalTariff = singleElectricalTariff;
        this.doubleElectricalTariffDay = doubleElectricalTariffDay;
        this.doubleElectricalTariffNight = doubleElectricalTariffNight;
        this.startLocalDate = new Date();
    }

    @Column(name = "water_tariff",nullable = false)
    public double getWaterTariff() {
        return waterTariff;
    }

    @Column(name = "single_electrical_tariff",nullable = false)
    public double getSingleElectricalTariff() {
        return singleElectricalTariff;
    }

    @Column(name = "double_electrical_tariff_day",nullable = false)
    public double getDoubleElectricalTariffDay() {
        return doubleElectricalTariffDay;
    }

    @Column(name = "double_electrical_tariff_night",nullable = false)
    public double getDoubleElectricalTariffNight() {
        return doubleElectricalTariffNight;
    }

    @Column(name = "start_LocalDate", nullable = false)
    public Date getStartLocalDate() {
        return startLocalDate;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tariff")
    public Set<TariffPayment> getTariffPayments() {
        return tariffPayments;
    }

    public void setTariffPayments(Set<TariffPayment> tariffPayments) {
        this.tariffPayments = tariffPayments;
    }

    public void setWaterTariff(double waterTariff) {
        this.waterTariff = waterTariff;
    }

    public void setSingleElectricalTariff(double singleElectricalTariff) {
        this.singleElectricalTariff = singleElectricalTariff;
    }

    public void setDoubleElectricalTariffDay(double doubleElectricalTariffDay) {
        this.doubleElectricalTariffDay = doubleElectricalTariffDay;
    }

    public void setDoubleElectricalTariffNight(double doubleElectricalTariffNight) {
        this.doubleElectricalTariffNight = doubleElectricalTariffNight;
    }

    protected void setStartLocalDate(Date startLocalDate){
        this.startLocalDate = startLocalDate;
    }

    protected Tariff() {
    }
}
