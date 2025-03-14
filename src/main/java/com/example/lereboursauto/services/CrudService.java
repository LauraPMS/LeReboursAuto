package com.example.lereboursauto.services;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
    void add(T entity);
    Optional<T> getById(int id);
    List<T> getAll();
    void update(T entity);
    void delete(int id);
}
