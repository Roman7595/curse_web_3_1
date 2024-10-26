package com.web.curse.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "membership_fees")
public class MembershipFee extends BaseEntity{
    private double contributionAmount;
    private Date startDate;
    private Date endDate;

    private Set<MembershipFeePayment> membershipFeePayments;

    public MembershipFee(double contributionAmount, Date endDate) {
        this.contributionAmount = contributionAmount;
        this.startDate = new Date();
        this.endDate = endDate;
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

    @OneToMany(mappedBy = "membership_fee",fetch = FetchType.LAZY)
    public Set<MembershipFeePayment> getMembershipFeePayments() {
        return membershipFeePayments;
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


    public void setMembershipFeePayments(Set<MembershipFeePayment> membershipFeePayments) {
        this.membershipFeePayments = membershipFeePayments;
    }

    protected MembershipFee() {
    }
}
