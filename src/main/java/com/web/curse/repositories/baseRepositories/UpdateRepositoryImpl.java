package com.web.curse.repositories.baseRepositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public abstract class UpdateRepositoryImpl<T> implements UpdateRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public T update(T entity) {
        entityManager.merge(entity);
        return entity;
    }
}
