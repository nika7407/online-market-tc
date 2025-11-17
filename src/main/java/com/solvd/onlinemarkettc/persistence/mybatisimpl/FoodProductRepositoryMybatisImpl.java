package com.solvd.onlinemarkettc.persistence.mybatisimpl;

import com.solvd.onlinemarkettc.domain.item.FoodProduct;
import com.solvd.onlinemarkettc.persistence.FoodProductRepository;
import com.solvd.onlinemarkettc.persistence.MyBatisHandler;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class FoodProductRepositoryMybatisImpl implements FoodProductRepository {

    @Override
    public Optional<FoodProduct> findById(Long id) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            FoodProductRepository foodProductRepository = session.getMapper(FoodProductRepository.class);
            return foodProductRepository.findById(id);
        }
    }

    @Override
    public Optional<FoodProduct> findByName(String name) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            FoodProductRepository foodProductRepository = session.getMapper(FoodProductRepository.class);
            return foodProductRepository.findByName(name);
        }
    }

    @Override
    public List<FoodProduct> findAll() {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            FoodProductRepository foodProductRepository = session.getMapper(FoodProductRepository.class);
            return foodProductRepository.findAll();
        }
    }

    @Override
    public Long save(FoodProduct foodProduct) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            FoodProductRepository foodProductRepository = session.getMapper(FoodProductRepository.class);
            return foodProductRepository.save(foodProduct);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            FoodProductRepository foodProductRepository = session.getMapper(FoodProductRepository.class);
            foodProductRepository.deleteById(id);
        }
    }

    @Override
    public Long update(FoodProduct foodProduct) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            FoodProductRepository foodProductRepository = session.getMapper(FoodProductRepository.class);
            return foodProductRepository.update(foodProduct);
        }
    }
}