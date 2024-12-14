package com.web.curse.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "membership_fee_payments")
public class MembershipFeePayment extends  BaseEntity{
    private double feeSum;
    private Date paymentLocalDate;
    private MembershipFee membershipFee;
    private Land land;

    public MembershipFeePayment(double feeSum, MembershipFee membershipFee, Land land) {
        this.feeSum = feeSum;
        this.paymentLocalDate = null;
        this.membershipFee = membershipFee;
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

    public void  setPaymentLocalDate(Date paymentLocalDate){
        this.paymentLocalDate = paymentLocalDate;
    }

    protected MembershipFeePayment() {
    }
}
