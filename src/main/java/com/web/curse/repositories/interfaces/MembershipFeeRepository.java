package com.web.curse.repositories.interfaces;


import com.web.curse.entities.MembershipFee;
import com.web.curse.entities.MembershipFeePayment;

import java.util.List;
import java.util.Optional;

public interface MembershipFeeRepository {
    public Optional<MembershipFee> findById(long id);
    public List<MembershipFee> findAll();
    public MembershipFee save(MembershipFee membershipFee);

    MembershipFee findByPayment(MembershipFeePayment membershipFeePayment);
}
