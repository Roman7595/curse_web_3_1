package com.web.curse.repositories.interfaces;


import com.web.curse.entities.MembershipFeePayment;

import java.util.List;
import java.util.Optional;

public interface MembershipFeePaymentRepository {
    public Optional<MembershipFeePayment> findById(long id);
    public List<MembershipFeePayment> findAll();
    public MembershipFeePayment save(MembershipFeePayment membershipFeePayment);
    public MembershipFeePayment update(MembershipFeePayment membershipFeePayment);
//    public List<MembershipFeePayment> whereClient(Client client);

}
