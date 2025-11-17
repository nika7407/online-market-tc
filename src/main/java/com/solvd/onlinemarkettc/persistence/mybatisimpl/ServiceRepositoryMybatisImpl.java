package com.solvd.onlinemarkettc.persistence.mybatisimpl;

import com.solvd.onlinemarkettc.domain.item.Service;
import com.solvd.onlinemarkettc.persistence.MyBatisHandler;
import com.solvd.onlinemarkettc.persistence.ServiceRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class ServiceRepositoryMybatisImpl implements ServiceRepository {

    @Override
    public Optional<Service> findById(Long id) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            ServiceRepository repository = session.getMapper(ServiceRepository.class);
            return repository.findById(id);
        }
    }

    @Override
    public Optional<Service> findByName(String name) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            ServiceRepository repository = session.getMapper(ServiceRepository.class);
            return repository.findByName(name);
        }
    }

    @Override
    public List<Service> findAll() {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            ServiceRepository repository = session.getMapper(ServiceRepository.class);
            return repository.findAll();
        }
    }

    @Override
    public Long save(Service service) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            ServiceRepository repository = session.getMapper(ServiceRepository.class);
            return repository.save(service);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            ServiceRepository repository = session.getMapper(ServiceRepository.class);
            repository.deleteById(id);
        }
    }

    @Override
    public Long update(Service service) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            ServiceRepository repository = session.getMapper(ServiceRepository.class);
            return repository.update(service);
        }
    }
}