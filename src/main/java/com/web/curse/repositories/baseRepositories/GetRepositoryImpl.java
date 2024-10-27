package com.web.curse.repositories.baseRepositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public abstract class GetRepositoryImpl<T> implements GetRepository<T>{

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public Optional<T> findById(int id, Class<T> entityClass){
        return Optional.of(entityManager.find(entityClass, id));
    }

    @Transactional
    public List<T> findAll(Class<T> entityClass){
        return entityManager.createQuery("Select t from " + entityClass.getName() + " t").getResultList();

    }

}
