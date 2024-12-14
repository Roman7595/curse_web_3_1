package com.web.curse.entities;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "tariff_payments")
public class TariffPayment extends  BaseEntity{
    private double waterUsage;
    private double singleElectricalUsage;
    private double doubleElectricalDayUsage;
    private double doubleElectricalNightUsage;
    private Date paymentLocalDate;
    private Tariff tariff;
    private Land land;

    private Date startLocalDate;

    private Date endLocalDate;
    public TariffPayment(double waterUsage, double singleElectricalUsage, double doubleElectricalDayUsage, double doubleElectricalNightUsage, Tariff tariff, Land land) {
        this.waterUsage = waterUsage;
        this.singleElectricalUsage = singleElectricalUsage;
        this.doubleElectricalDayUsage = doubleElectricalDayUsage;
        this.doubleElectricalNightUsage = doubleElectricalNightUsage;
        this.paymentLocalDate = null;
        this.tariff = tariff;
        this.land = land;
        Calendar cal = Calendar.getInstance();
        this.startLocalDate = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        this.endLocalDate =  cal.getTime();
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


    @Column(name = "payment_LocalDate")
    public Date getPaymentLocalDate() {
        return paymentLocalDate;
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

    @Column(name = "start_local_date")
    public Date getStartLocalDate() {
        return startLocalDate;
    }
    @Column(name = "end_local_date")
    public Date getEndLocalDate() {
        return endLocalDate;
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

    public void setEndLocalDate(Date endLocalDate) {
        this.endLocalDate = endLocalDate;
    }

    public void setStartLocalDate(Date startLocalDate) {
        this.startLocalDate = startLocalDate;
    }

    public void setPaymentLocalDate(Date paymentLocalDate){
        this.paymentLocalDate = paymentLocalDate;
    }

    protected TariffPayment() {
    }
}
