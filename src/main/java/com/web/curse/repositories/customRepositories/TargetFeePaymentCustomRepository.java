package com.web.curse.repositories.customRepositories;

import com.web.curse.entities.Land;
import com.web.curse.entities.TargetFee;
import com.web.curse.entities.TargetFeePayment;
import com.web.curse.entities.TariffPayment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TargetFeePaymentCustomRepository extends Repository<TargetFeePayment,Long> {

    @Query(value = "select tfp from TargetFeePayment tfp join tfp.land l join tfp.targetFee tf where l = :land and tf = :targetFee")
    TargetFeePayment findByLandAndTargetFee(@Param(value = "land") Land land, @Param(value = "targetFee") TargetFee targetFee);

    @Query(value = "select tfp from TargetFeePayment tfp join tfp.land l where l = :land")
    List<TargetFeePayment> findByLand(@Param(value = "land") Land land);


}
