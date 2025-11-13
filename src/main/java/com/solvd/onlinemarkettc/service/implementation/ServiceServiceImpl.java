package com.solvd.onlinemarkettc.service.implementation;

import com.solvd.onlinemarkettc.domain.item.Service;
import com.solvd.onlinemarkettc.persistence.repository.ServiceRepository;
import com.solvd.onlinemarkettc.service.interfaces.ServiceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

// ik its stupid BUT idc
public class ServiceServiceImpl implements ServiceService {

    private static final Logger log = LogManager.getLogger(ServiceServiceImpl.class);
    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl() {
        this.serviceRepository = new ServiceRepository();
    }

    public Service createService(Service service) {
        log.info("create service: {}", service.getName());
        return serviceRepository.save(service);
    }

    public Optional<Service> getServiceById(Long id) {
        log.debug("find service id: {}", id);
        return serviceRepository.findById(id);
    }

    public Optional<Service> getServiceByName(String name) {
        log.debug("find service name: {}", name);
        return serviceRepository.findByName(name);
    }

    public List<Service> getAllServices() {
        log.debug("get all services");
        return serviceRepository.findAll();
    }

    public Service updateService(Service service) {
        log.info("update service id: {}", service.getId());
        return serviceRepository.update(service);
    }

    public void deleteService(Long id) {
        log.info("delete service id: {}", id);
        serviceRepository.deleteById(id);
    }

    public boolean serviceExists(Long id) {
        return serviceRepository.findById(id).isPresent();
    }

    public boolean serviceExistsByName(String name) {
        return serviceRepository.findByName(name).isPresent();
    }
}