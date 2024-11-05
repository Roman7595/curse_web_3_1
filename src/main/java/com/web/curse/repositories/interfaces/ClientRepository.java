package com.web.curse.repositories.interfaces;


import com.web.curse.entities.*;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    public Client save(Client client);
    public List<Client> findAll();

    public Optional<Client> findById(long id);
    Client findByLand(Land land);

    Client findByLogin(String login);
//    public List<Client> whereTariff(Tariff tariff);
//    public List<Client> whereMembershipFee(MembershipFee membershipFee);
//    public List<Client> whereTargetFee(TargetFee targetFee);
}
