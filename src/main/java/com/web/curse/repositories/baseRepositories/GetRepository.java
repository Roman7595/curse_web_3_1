package com.web.curse.repositories.baseRepositories;

import java.util.List;
import java.util.Optional;

public interface GetRepository<T>{
    public Optional<T> findById(int id, Class<T> type);
    public List<T> findAll(Class<T> type);
}
