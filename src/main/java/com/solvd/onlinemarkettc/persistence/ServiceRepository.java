package com.solvd.onlinemarkettc.persistence;

import com.solvd.onlinemarkettc.domain.item.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository {

    Optional<Service> findById(Long id);

    Optional<Service> findByName(String name);

    List<Service> findAll();

    Service save(Service service);

    void deleteById(Long id);

    Service update(Service service);

}