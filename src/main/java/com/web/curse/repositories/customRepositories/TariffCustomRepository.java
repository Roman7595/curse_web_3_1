package com.web.curse.repositories.customRepositories;


import com.web.curse.entities.Tariff;
import com.web.curse.entities.TariffPayment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface TariffCustomRepository extends Repository<Tariff, Long> {
    @Query(value = "select t from Tariff t order by t.startLocalDate desc limit 1")
    Tariff findLast();
    @Query(value = "select t from Tariff t join t.tariffPayments tp where tp = :tariffPayment")
    Tariff findByPayment(@Param(value = "tariffPayment") TariffPayment tariffPayment);
}
