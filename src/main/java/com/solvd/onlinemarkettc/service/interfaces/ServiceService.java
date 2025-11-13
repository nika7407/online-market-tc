package com.solvd.onlinemarkettc.service.interfaces;

import com.solvd.onlinemarkettc.domain.item.Service;

import java.util.List;
import java.util.Optional;

// ik its stupid name
public interface ServiceService {
    Service createService(Service service);
    Optional<Service> getServiceById(Long id);
    Optional<Service> getServiceByName(String name);
    List<Service> getAllServices();
    Service updateService(Service service);
    void deleteService(Long id);
    boolean serviceExists(Long id);
    boolean serviceExistsByName(String name);
}