package com.web.curse.repositories.customRepositories;


import com.web.curse.entities.Client;
import com.web.curse.entities.Land;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface ClientCustomRepository extends Repository<Client, Long> {
    @Query(value = "select c from Land l join l.client c where l = :land")
    Client findByLand(@Param(value = "land") Land land);
    @Query(value = "select c from Client c where c.login = :login")
    Client findByLogin(@Param(value = "login") String login);
}
