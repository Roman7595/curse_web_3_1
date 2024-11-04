package com.web.curse.repositories.baseRepositories;

public interface SaveRepository<T>{
    public T save(T entity);
}
