package com.web.curse.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "membership_fees")
public class MembershipFee extends BaseEntity{
    private double contributionAmount;
    private Date startLocalDate;
    private Date endLocalDate;

    private Set<MembershipFeePayment> membershipFeePayments;

    public MembershipFee(double contributionAmount) {
        this.contributionAmount = contributionAmount;
        Calendar cal = Calendar.getInstance();
        this.startLocalDate = cal.getTime();
        cal.add(Calendar.YEAR, 1);
        this.endLocalDate = cal.getTime();
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

    @OneToMany(mappedBy = "membershipFee",fetch = FetchType.LAZY)
    public Set<MembershipFeePayment> getMembershipFeePayments() {
        return membershipFeePayments;
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


    public void setMembershipFeePayments(Set<MembershipFeePayment> membershipFeePayments) {
        this.membershipFeePayments = membershipFeePayments;
    }

    protected MembershipFee() {
    }
}
