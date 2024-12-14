package com.web.curse.dtos.out;

import java.util.Date;

public class MembershipFeeOutputDto {
    public long id;
    public double contributionAmount;
    public Date startLocalDate;
    public Date endLocalDate;

    protected MembershipFeeOutputDto(){}
    public MembershipFeeOutputDto(long id, double contributionAmount, Date startLocalDate, Date endLocalDate) {
        this.id = id;
        this.contributionAmount = contributionAmount;
        this.startLocalDate = startLocalDate;
        this.endLocalDate = endLocalDate;
    }

}
