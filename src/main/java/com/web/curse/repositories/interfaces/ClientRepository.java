package com.web.curse.repositories.interfaces;


import com.web.curse.entities.*;
import com.web.curse.entities.enums.ClientRoles;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Client save(Client client);
    List<Client> findAll();

    Optional<Client> findById(long id);
    Client findByLand(Land land);

    Client findByLogin(String login);
}
