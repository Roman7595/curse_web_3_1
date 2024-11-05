package com.web.curse.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tariff_payments")
public class TariffPayment extends  BaseEntity{
    private double waterUsage;
    private double singleElectricalUsage;
    private double doubleElectricalDayUsage;
    private double doubleElectricalNightUsage;
    private Date paymentDate;
    private Tariff tariff;
    private Land land;

    public TariffPayment(double waterUsage, double singleElectricalUsage, double doubleElectricalDayUsage, double doubleElectricalNightUsage, Tariff tariff, Land land) {
        this.waterUsage = waterUsage;
        this.singleElectricalUsage = singleElectricalUsage;
        this.doubleElectricalDayUsage = doubleElectricalDayUsage;
        this.doubleElectricalNightUsage = doubleElectricalNightUsage;
        this.paymentDate = null;
        this.tariff = tariff;
        this.land = land;
    }


    @Column(name = "water_usage",nullable = false)
    public double getWaterUsage() {
        return waterUsage;
    }


    @Column(name = "single_electrical_usage",nullable = false)
    public double getSingleElectricalUsage() {
        return singleElectricalUsage;
    }


    @Column(name = "double_electrical_day_usage",nullable = false)
    public double getDoubleElectricalDayUsage() {
        return doubleElectricalDayUsage;
    }


    @Column(name = "double_electrical_night_usage",nullable = false)
    public double getDoubleElectricalNightUsage() {
        return doubleElectricalNightUsage;
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


    public void setSingleElectricalUsage(double singleElectricalUsage) {
        this.singleElectricalUsage = singleElectricalUsage;
    }


    public void setDoubleElectricalDayUsage(double doubleElectricalDayUsage) {
        this.doubleElectricalDayUsage = doubleElectricalDayUsage;
    }


    public void setDoubleElectricalNightUsage(double doubleElectricalNightUsage) {
        this.doubleElectricalNightUsage = doubleElectricalNightUsage;
    }


    protected void setPaymentDate(Date paymentDate){
        this.paymentDate = paymentDate;
    }

}
