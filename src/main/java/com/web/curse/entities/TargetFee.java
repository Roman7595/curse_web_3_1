package com.web.curse.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "target_fees")
public class TargetFee extends BaseEntity{
    private String targetName;
    private double contributionAmount;
    private Date startLocalDate;
    private Date endLocalDate;
    private Set<TargetFeePayment> targetFeePayments;

    public TargetFee(String targetName, double contributionAmount, Date endLocalDate) {
        this.targetName = targetName;
        this.contributionAmount = contributionAmount;
        this.startLocalDate = new Date();
        this.endLocalDate = endLocalDate;
    }

    @Column(name = "target_name",nullable = false)
    public String getTargetName(){
        return targetName;
    }

    @Column(name = "contribution_amount", nullable = false)
    public double getContributionAmount() {
        return contributionAmount;
    }

    @Column(name = "start_LocalDate", nullable = false)
    public Date getStartLocalDate() {
        return startLocalDate;
    }

    @Column(name = "end_LocalDate", nullable = false)
    public Date getEndLocalDate() {
        return endLocalDate;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "targetFee")
    public Set<TargetFeePayment> getTargetFeePayments() {
        return targetFeePayments;
    }

    public void setTargetFeePayments(Set<TargetFeePayment> targetFeePayments) {
        this.targetFeePayments = targetFeePayments;
    }

    public void setTargetName(String targetName){
        this.targetName = targetName;
    }
    public void setContributionAmount(double contributionAmount) {
        this.contributionAmount = contributionAmount;
    }

    public void setStartLocalDate(Date startLocalDate) {
        this.startLocalDate = startLocalDate;
    }

    public void setEndLocalDate(Date endLocalDate) {
        this.endLocalDate = endLocalDate;
    }

    protected TargetFee() {
    }
}
