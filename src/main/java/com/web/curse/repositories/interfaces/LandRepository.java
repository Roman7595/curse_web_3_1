package com.web.curse.repositories.interfaces;

import com.web.curse.entities.Client;
import com.web.curse.entities.Land;

import java.util.List;
import java.util.Optional;

public interface LandRepository {
    public Optional<Land> findById(long id);
    public List<Land> findAll();
    public Land save(Land land);
    public Land update(Land land);

    public List<Land> findByClient(Client client);

    public Land findByNumber(String number);
}
