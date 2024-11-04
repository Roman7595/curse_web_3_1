package com.web.curse.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "target_fee_payments")
public class TargetFeePayment extends  BaseEntity{
    private double feeSum;
    private Date paymentDate;
    private TargetFee targetFee;
    private Land land;

    public TargetFeePayment(double feeSum, TargetFee targetFee, Land land) {
        this.feeSum = feeSum;
        this.paymentDate = new Date();
        this.targetFee = targetFee;
        this.land = land;
    }

    @Column(name = "fee_sum", nullable = false)
    public double getFeeSum() {
        return feeSum;
    }

    @Column(name = "payment_date", nullable = false)
    public Date getPaymentDate() {
        return paymentDate;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "target_fee_id", nullable = false)
    public TargetFee getTargetFee() {
        return targetFee;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "land_id", nullable = false)
    public Land getLand() {
        return land;
    }

    public void setFeeSum(double feeSum) {
        this.feeSum = feeSum;
    }

    public void setTargetFee(TargetFee targetFee) {
        this.targetFee = targetFee;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    protected void setPaymentDate(Date paymentDate){
        this.paymentDate = paymentDate;
    }
}
