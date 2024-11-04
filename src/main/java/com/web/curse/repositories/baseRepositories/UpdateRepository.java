package com.web.curse.repositories.baseRepositories;

public interface UpdateRepository<T> {
    public T update(T entity);
}
