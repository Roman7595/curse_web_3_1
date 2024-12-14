package com.web.curse.repositories.customRepositories;

import com.web.curse.entities.Land;
import com.web.curse.entities.MembershipFee;
import com.web.curse.entities.MembershipFeePayment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MembershipFeePaymentCustomRepository extends Repository<MembershipFeePayment,Long> {

    @Query(value = "select mfp from MembershipFeePayment mfp join mfp.land l join mfp.membershipFee mf where l = :land and mf = :membershipFee")
    MembershipFeePayment findByLandAndMembershipFee(@Param(value = "land") Land land, @Param(value = "membershipFee") MembershipFee membershipFee);

    @Query(value = "select mfp from MembershipFeePayment mfp join mfp.land l where l = :land")
    List<MembershipFeePayment> findByLand(@Param(value = "land") Land land);

}
