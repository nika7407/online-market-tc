package com.solvd.onlinemarkettc.persistence.mybatisimpl;

import com.solvd.onlinemarkettc.domain.item.NonPerishebleProduct;
import com.solvd.onlinemarkettc.persistence.MyBatisHandler;
import com.solvd.onlinemarkettc.persistence.NonPerishableProductRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class NonPerishableProductRepositoryMybatisImpl implements NonPerishableProductRepository {

    @Override
    public Optional<NonPerishebleProduct> findById(Long id) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            NonPerishableProductRepository repository = session.getMapper(NonPerishableProductRepository.class);
            return repository.findById(id);
        }
    }

    @Override
    public Optional<NonPerishebleProduct> findByName(String name) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            NonPerishableProductRepository repository = session.getMapper(NonPerishableProductRepository.class);
            return repository.findByName(name);
        }
    }

    @Override
    public List<NonPerishebleProduct> findAll() {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            NonPerishableProductRepository repository = session.getMapper(NonPerishableProductRepository.class);
            return repository.findAll();
        }
    }

    @Override
    public Long save(NonPerishebleProduct nonPerishableProduct) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            NonPerishableProductRepository repository = session.getMapper(NonPerishableProductRepository.class);
            return repository.save(nonPerishableProduct);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            NonPerishableProductRepository repository = session.getMapper(NonPerishableProductRepository.class);
            repository.deleteById(id);
        }
    }

    @Override
    public Long update(NonPerishebleProduct nonPerishableProduct) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            NonPerishableProductRepository repository = session.getMapper(NonPerishableProductRepository.class);
            return repository.update(nonPerishableProduct);
        }
    }
}