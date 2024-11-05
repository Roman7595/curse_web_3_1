package com.web.curse.repositories.customRepositories;


import com.web.curse.entities.Land;
import com.web.curse.entities.TariffPayment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TariffPaymentCustomRepository extends Repository<TariffPayment, Long> {
    @Query(value = "select tp from TariffPayment tp join tp.land l where l = :land")
    List<TariffPayment> findByLand(@Param(value = "land") Land land);

}
