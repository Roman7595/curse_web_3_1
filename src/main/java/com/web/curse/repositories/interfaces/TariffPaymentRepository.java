package com.web.curse.repositories.interfaces;


import com.web.curse.entities.Land;
import com.web.curse.entities.TariffPayment;

import java.util.List;
import java.util.Optional;

public interface TariffPaymentRepository {
    public Optional<TariffPayment> findById(long id);
    public List<TariffPayment> findAll();
    public TariffPayment save(TariffPayment tariffPayment);
    public TariffPayment update(TariffPayment tariffPayment);
    public List<TariffPayment> findByLand(Land tariffPayment);

}
