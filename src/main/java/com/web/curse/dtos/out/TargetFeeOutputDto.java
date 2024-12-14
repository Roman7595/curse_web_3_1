package com.web.curse.dtos.out;

import com.web.curse.entities.TargetFeePayment;

import java.util.Date;
import java.util.Set;

public class TargetFeeOutputDto {
    public long id;
    public String targetName;
    public double contributionAmount;
    public Date startLocalDate;
    public Date endLocalDate;

    public TargetFeeOutputDto(long id, String targetName, double contributionAmount, Date startLocalDate, Date endLocalDate) {
        this.id = id;
        this.targetName = targetName;
        this.contributionAmount = contributionAmount;
        this.startLocalDate = startLocalDate;
        this.endLocalDate = endLocalDate;
    }

    protected TargetFeeOutputDto() {
    }
}
