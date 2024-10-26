package com.web.curse.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tarif_payments")
public class TariffPayment extends  BaseEntity{
    private double waterUsage;
    private double waterSum;
    private double singleElectricalUsage;
    private double singleElectricalSum;
    private double doubleElectricalDayUsage;
    private double doubleElectricalDaySum;
    private double doubleElectricalNightUsage;
    private double doubleElectricalNightSum;
    private final Date paymentDate;
    private Tariff tariff;
    private Land land;

    public TariffPayment(double waterUsage, double waterSum, double singleElectricalUsage, double singleElectricalSum, double doubleElectricalDayUsage, double doubleElectricalDaySum, double doubleElectricalNightUsage, double doubleElectricalNightSum,Tariff tariff,Land land) {
        this.waterUsage = waterUsage;
        this.waterSum = waterSum;
        this.singleElectricalUsage = singleElectricalUsage;
        this.singleElectricalSum = singleElectricalSum;
        this.doubleElectricalDayUsage = doubleElectricalDayUsage;
        this.doubleElectricalDaySum = doubleElectricalDaySum;
        this.doubleElectricalNightUsage = doubleElectricalNightUsage;
        this.doubleElectricalNightSum = doubleElectricalNightSum;
        this.paymentDate = new Date();
        this.tariff = tariff;
        this.land = land;
    }


    @Column(name = "water_usage",nullable = false)
    public double getWaterUsage() {
        return waterUsage;
    }

    @Column(name = "water_sum",nullable = false)
    public double getWaterSum() {
        return waterSum;
    }

    @Column(name = "single_electrical_usage",nullable = false)
    public double getSingleElectricalUsage() {
        return singleElectricalUsage;
    }

    @Column(name = "single_electrical_sum",nullable = false)
    public double getSingleElectricalSum() {
        return singleElectricalSum;
    }

    @Column(name = "double_electrical_day_usage",nullable = false)
    public double getDoubleElectricalDayUsage() {
        return doubleElectricalDayUsage;
    }

    @Column(name = "double_electrical_day_sum",nullable = false)
    public double getDoubleElectricalDaySum() {
        return doubleElectricalDaySum;
    }

    @Column(name = "double_electrical_night_usage",nullable = false)
    public double getDoubleElectricalNightUsage() {
        return doubleElectricalNightUsage;
    }

    @Column(name = "double_electrical_night_sum",nullable = false)
    public double getDoubleElectricalNightSum() {
        return doubleElectricalNightSum;
    }

    @Column(name = "payment_date")
    public Date getPaymentDate() {
        return paymentDate;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tariff_id",nullable = false)
    public Tariff getTariff() {
        return tariff;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "land_id",nullable = false)
    public Land getLand() {
        return land;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public void setWaterUsage(double waterUsage) {
        this.waterUsage = waterUsage;
    }

    public void setWaterSum(double waterSum) {
        this.waterSum = waterSum;
    }

    public void setSingleElectricalUsage(double singleElectricalUsage) {
        this.singleElectricalUsage = singleElectricalUsage;
    }

    public void setSingleElectricalSum(double singleElectricalSum) {
        this.singleElectricalSum = singleElectricalSum;
    }

    public void setDoubleElectricalDayUsage(double doubleElectricalDayUsage) {
        this.doubleElectricalDayUsage = doubleElectricalDayUsage;
    }

    public void setDoubleElectricalDaySum(double doubleElectricalDaySum) {
        this.doubleElectricalDaySum = doubleElectricalDaySum;
    }

    public void setDoubleElectricalNightUsage(double doubleElectricalNightUsage) {
        this.doubleElectricalNightUsage = doubleElectricalNightUsage;
    }

    public void setDoubleElectricalNightSum(double doubleElectricalNightSum) {
        this.doubleElectricalNightSum = doubleElectricalNightSum;
    }
}
