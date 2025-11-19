package com.solvd.onlinemarkettc.service.impl;

import com.solvd.onlinemarkettc.domain.item.Service;
import com.solvd.onlinemarkettc.persistence.mybatisimpl.ServiceRepositoryMybatisImpl;
import com.solvd.onlinemarkettc.service.ServiceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

// ik its stupid BUT idc
public class ServiceServiceImpl implements ServiceService {

    private static final Logger log = LogManager.getLogger(ServiceServiceImpl.class);
    // private final ServiceRepositoryImpl serviceRepository;
    private final ServiceRepositoryMybatisImpl serviceRepository;

    public ServiceServiceImpl() {
        this.serviceRepository = new ServiceRepositoryMybatisImpl();
    }

    @Override
    public Long createService(Service service) {
        log.info("create service: {}", service.getName());
        serviceRepository.save(service);
        return service.getId();
    }

    @Override
    public Service getServiceById(Long id) {
        log.debug("find service id: {}", id);
        return serviceRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Service getServiceByName(String name) {
        log.debug("find service name: {}", name);
        return serviceRepository.findByName(name).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Service> getAllServices() {
        log.debug("get all services");
        return serviceRepository.findAll();
    }

    @Override
    public Long updateService(Service service) {
        log.info("update service id: {}", service.getId());
        return serviceRepository.update(service);
    }

    @Override
    public void deleteService(Long id) {
        log.info("delete service id: {}", id);
        serviceRepository.deleteById(id);
    }

    @Override
    public boolean serviceExists(Long id) {
        return serviceRepository.findById(id).isPresent();
    }

    @Override
    public boolean serviceExistsByName(String name) {
        return serviceRepository.findByName(name).isPresent();
    }
}