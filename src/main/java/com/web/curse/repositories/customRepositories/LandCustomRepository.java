package com.web.curse.repositories.customRepositories;


import com.web.curse.entities.Client;
import com.web.curse.entities.Land;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LandCustomRepository extends Repository<Land, Long> {
    @Query(value = "select l from Land l join l.client c where c = :client")
    List<Land> findByClient(@Param(value = "client") Client client);

    @Query(value = "select l from Land l where l.number = :number")
    Land findByNumber(@Param(value = "number") String number);

}
