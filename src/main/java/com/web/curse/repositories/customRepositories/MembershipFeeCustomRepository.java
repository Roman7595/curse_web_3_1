package com.web.curse.repositories.customRepositories;


import com.web.curse.entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface MembershipFeeCustomRepository extends Repository<MembershipFee, Long> {
    @Query(value = "select m from MembershipFee m join m.membershipFeePayments mp where mp = :membershipFeePayments")
    MembershipFee findByPayment(@Param(value = "membershipFeePayments") MembershipFeePayment membershipFeePayment);
}
