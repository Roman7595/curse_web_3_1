package com.web.curse.repositories.customRepositories;


import com.web.curse.entities.Land;
import com.web.curse.entities.TariffPayment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TariffPaymentCustomRepository extends Repository<TariffPayment, Long> {
    @Query(value = "select tp from TariffPayment tp join tp.land l where l = :land")
    List<TariffPayment> findByLand(@Param(value = "land") Land land);

    @Query(value = "select tp from TariffPayment tp join tp.land l where l = :land and tp.endLocalDate = :endLocalDate")
    TariffPayment findLast(@Param(value = "land")Land land, @Param(value = "endLocalDate") Date endLocalDate);
}
