package com.solvd.onlinemarkettc.persistence.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    Optional<T> findById(Long id);

    List<T> findAll();

    T save(T t);

    void deleteById(Long id);

    T update(T t);
}
