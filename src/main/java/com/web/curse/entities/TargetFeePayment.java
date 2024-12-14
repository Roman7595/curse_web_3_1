package com.web.curse.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "target_fee_payments")
public class TargetFeePayment extends  BaseEntity{
    private double feeSum;
    private Date paymentLocalDate;
    private TargetFee targetFee;
    private Land land;

    public TargetFeePayment(double feeSum, TargetFee targetFee, Land land) {
        this.feeSum = feeSum;
        this.paymentLocalDate = null;
        this.targetFee = targetFee;
        this.land = land;
    }

    @Column(name = "fee_sum", nullable = false)
    public double getFeeSum() {
        return feeSum;
    }

    @Column(name = "payment_LocalDate")
    public Date getPaymentLocalDate() {
        return paymentLocalDate;
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


    public void setPaymentLocalDate(Date paymentLocalDate){
        this.paymentLocalDate = paymentLocalDate;
    }

    protected TargetFeePayment() {
    }
}
