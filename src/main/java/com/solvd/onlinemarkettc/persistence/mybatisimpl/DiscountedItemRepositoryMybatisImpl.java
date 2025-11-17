package com.solvd.onlinemarkettc.persistence.mybatisimpl;

import com.solvd.onlinemarkettc.domain.item.DiscountedItem;
import com.solvd.onlinemarkettc.persistence.DiscountedItemRepository;
import com.solvd.onlinemarkettc.persistence.MyBatisHandler;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class DiscountedItemRepositoryMybatisImpl implements DiscountedItemRepository {

    @Override
    public Optional<DiscountedItem> findById(Long id) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            DiscountedItemRepository discountedItemRepository = session.getMapper(DiscountedItemRepository.class);
            return discountedItemRepository.findById(id);
        }
    }

    @Override
    public Optional<DiscountedItem> findByName(String name) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            DiscountedItemRepository discountedItemRepository = session.getMapper(DiscountedItemRepository.class);
            return discountedItemRepository.findByName(name);
        }
    }

    @Override
    public List<DiscountedItem> findAll() {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            DiscountedItemRepository discountedItemRepository = session.getMapper(DiscountedItemRepository.class);
            return discountedItemRepository.findAll();
        }
    }

    @Override
    public Long save(DiscountedItem discountedItem) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            DiscountedItemRepository discountedItemRepository = session.getMapper(DiscountedItemRepository.class);
            return discountedItemRepository.save(discountedItem);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            DiscountedItemRepository discountedItemRepository = session.getMapper(DiscountedItemRepository.class);
            discountedItemRepository.deleteById(id);
        }
    }

    @Override
    public Long update(DiscountedItem discountedItem) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            DiscountedItemRepository discountedItemRepository = session.getMapper(DiscountedItemRepository.class);
            return discountedItemRepository.update(discountedItem);
        }
    }
}