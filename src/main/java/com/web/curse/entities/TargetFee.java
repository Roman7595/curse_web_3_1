package com.web.curse.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "target_fees")
public class TargetFee extends BaseEntity{
    private String targetName;
    private double contributionAmount;
    private Date startDate;
    private Date endDate;
    private Set<TargetFeePayment> targetFeePayments;

    public TargetFee(String targetName, double contributionAmount, Date endDate) {
        this.targetName = targetName;
        this.contributionAmount = contributionAmount;
        this.startDate = new Date();
        this.endDate = endDate;
    }

    @Column(name = "target_name",nullable = false)
    public String getTargetName(){
        return targetName;
    }

    @Column(name = "contribution_amount", nullable = false)
    public double getContributionAmount() {
        return contributionAmount;
    }

    @Column(name = "start_date", nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    @Column(name = "end_date", nullable = false)
    public Date getEndDate() {
        return endDate;
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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    protected TargetFee() {
    }
}
