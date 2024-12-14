package com.web.curse.repositories.interfaces;


import com.web.curse.entities.Land;
import com.web.curse.entities.TargetFee;
import com.web.curse.entities.TargetFeePayment;

import java.util.List;
import java.util.Optional;

public interface TargetFeePaymentRepository {
    public Optional<TargetFeePayment> findById(long id);
    public List<TargetFeePayment> findAll();
    public TargetFeePayment save(TargetFeePayment targetFeePayment);
    public TargetFeePayment update(TargetFeePayment land);
    public TargetFeePayment findByLandAndTargetFee(Land land, TargetFee targetFee);

    List<TargetFeePayment> findByLand(Land land);

}
