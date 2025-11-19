package com.solvd.onlinemarkettc.service;

import com.solvd.onlinemarkettc.domain.item.Service;

import java.util.List;

// ik its stupid name
public interface ServiceService {

    Long createService(Service service);

    Service getServiceById(Long id);

    Service getServiceByName(String name);

    List<Service> getAllServices();

    Long updateService(Service service);

    void deleteService(Long id);

    boolean serviceExists(Long id);

    boolean serviceExistsByName(String name);
}