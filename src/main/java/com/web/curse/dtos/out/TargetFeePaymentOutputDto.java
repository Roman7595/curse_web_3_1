package com.web.curse.dtos.out;

import com.web.curse.entities.Land;
import com.web.curse.entities.TargetFee;

import java.util.Date;

public class TargetFeePaymentOutputDto {
    public long id;
    public double feeSum;
    public Date paymentLocalDate;

    public TargetFeePaymentOutputDto(long id,double feeSum, Date paymentLocalDate) {
        this.id = id;
        this.feeSum = feeSum;
        this.paymentLocalDate = paymentLocalDate;
    }

    protected TargetFeePaymentOutputDto() {
    }
}
