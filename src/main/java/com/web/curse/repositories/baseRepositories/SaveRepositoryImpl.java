package com.web.curse.repositories.baseRepositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public abstract class SaveRepositoryImpl<T> implements SaveRepository<T>{
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public T save(T entity){
        entityManager.persist(entity);
        return entity;
    }
}
