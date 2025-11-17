package com.solvd.onlinemarkettc.persistence;

import com.solvd.onlinemarkettc.domain.item.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository {

    Optional<Service> findById(Long id);

    Optional<Service> findByName(String name);

    List<Service> findAll();

    Long save(Service service);

    void deleteById(Long id);

    Long update(Service service);

}