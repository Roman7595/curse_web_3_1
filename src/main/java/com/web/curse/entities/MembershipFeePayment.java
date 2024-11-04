package com.web.curse.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "membership_fee_payments")
public class MembershipFeePayment extends  BaseEntity{
    private double feeSum;
    private Date paymentDate;
    private MembershipFee membershipFee;
    private Land land;

    public MembershipFeePayment(double feeSum, MembershipFee membershipFee, Land land) {
        this.feeSum = feeSum;
        this.paymentDate = new Date();
        this.membershipFee = membershipFee;
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

    @JoinColumn(name = "membership_fee_id",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public MembershipFee getMembershipFee() {
        return membershipFee;
    }

    @JoinColumn(name = "land_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    public Land getLand() {
        return land;
    }

    public void setFeeSum(double feeSum) {
        this.feeSum = feeSum;
    }

    public void setMembershipFee(MembershipFee membershipFee) {
        this.membershipFee = membershipFee;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    protected void  setPaymentDate(Date paymentDate){
        this.paymentDate = paymentDate;
    }

    protected MembershipFeePayment() {
    }
}
