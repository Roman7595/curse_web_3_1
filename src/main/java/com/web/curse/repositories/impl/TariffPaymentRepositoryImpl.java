package com.web.curse.repositories.impl;


import com.web.curse.entities.Land;
import com.web.curse.entities.TariffPayment;
import com.web.curse.repositories.baseRepositories.GetRepository;
import com.web.curse.repositories.baseRepositories.SaveRepository;
import com.web.curse.repositories.baseRepositories.UpdateRepository;
import com.web.curse.repositories.customRepositories.TariffPaymentCustomRepository;
import com.web.curse.repositories.interfaces.TariffPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class TariffPaymentRepositoryImpl implements TariffPaymentRepository {
    @Autowired
    GetRepository<TariffPayment> getRepository;

    @Autowired
    SaveRepository<TariffPayment> saveRepository;

    @Autowired
    UpdateRepository<TariffPayment> updateRepository;

    @Autowired
    TariffPaymentCustomRepository tariffPaymentCustomRepository;

    @Override
    public Optional<TariffPayment> findById(long id) {
        return getRepository.findById(id, TariffPayment.class);
    }

    @Override
    public List<TariffPayment> findAll() {
        return getRepository.findAll(TariffPayment.class);
    }

    @Override
    public TariffPayment save(TariffPayment tariffPayment) {
        return saveRepository.save(tariffPayment);
    }

    @Override
    public TariffPayment update(TariffPayment tariffPayment) {
        return updateRepository.update(tariffPayment);
    }

    @Override
    public List<TariffPayment> findByLand(Land land) {
        return tariffPaymentCustomRepository.findByLand(land);
    }
}
