package com.web.curse.dtos.out;

import com.web.curse.entities.Land;
import com.web.curse.entities.MembershipFee;

import java.util.Date;

public class MembershipFeePaymentOutputDto {
    public long id;
    public double feeSum;
    public Date paymentLocalDate;

    public MembershipFeePaymentOutputDto(long id,double feeSum, Date paymentLocalDate) {
        this.id = id;
        this.feeSum = feeSum;
        this.paymentLocalDate = paymentLocalDate;
    }

    protected MembershipFeePaymentOutputDto() {
    }
}
