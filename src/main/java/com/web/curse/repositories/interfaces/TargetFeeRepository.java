package com.web.curse.repositories.interfaces;


import com.web.curse.entities.TargetFee;
import com.web.curse.entities.TargetFeePayment;

import java.util.List;
import java.util.Optional;

public interface TargetFeeRepository {
    public Optional<TargetFee> findById(long id);
    public List<TargetFee> findAll();
    public TargetFee save(TargetFee targetFee);
    TargetFee findByPayment(TargetFeePayment targetFeePayment);
}
