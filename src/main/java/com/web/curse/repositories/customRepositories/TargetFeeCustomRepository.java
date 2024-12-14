package com.web.curse.repositories.customRepositories;


import com.web.curse.entities.TargetFee;
import com.web.curse.entities.TargetFeePayment;
import com.web.curse.entities.Tariff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface TargetFeeCustomRepository extends Repository<TargetFee, Long> {
    @Query(value = "select t from TargetFee t join t.targetFeePayments tp where tp = :targetFeePayments")
    TargetFee findByPayment(@Param(value = "targetFeePayments") TargetFeePayment targetFeePayment);
}
