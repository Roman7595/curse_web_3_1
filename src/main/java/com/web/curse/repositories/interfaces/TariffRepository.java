package com.web.curse.repositories.interfaces;


import com.web.curse.entities.Tariff;

import java.util.List;
import java.util.Optional;

public interface TariffRepository {
    public Optional<Tariff> findById(long id);
    public List<Tariff> findAll();
    public Tariff save(Tariff tariff);
}
