package com.solvd.onlinemarkettc.persistence.mybatisimpl;

import com.solvd.onlinemarkettc.domain.finantialoperation.Basket;
import com.solvd.onlinemarkettc.persistence.BasketRepository;
import com.solvd.onlinemarkettc.persistence.MyBatisHandler;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class BasketRepositoryMybatisImpl implements BasketRepository {

    @Override
    public Optional<Basket> findById(Long id) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            BasketRepository basketRepository = session.getMapper(BasketRepository.class);
            return basketRepository.findById(id);
        }
    }

    @Override
    public List<Basket> findAll() {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            BasketRepository basketRepository = session.getMapper(BasketRepository.class);
            return basketRepository.findAll();
        }
    }

    @Override
    public Long save(Basket basket) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            BasketRepository basketRepository = session.getMapper(BasketRepository.class);
            return basketRepository.save(basket);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            BasketRepository basketRepository = session.getMapper(BasketRepository.class);
            basketRepository.deleteById(id);
        }
    }

    @Override
    public Long update(Basket basket) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            BasketRepository basketRepository = session.getMapper(BasketRepository.class);
            return basketRepository.update(basket);
        }
    }

    @Override
    public Basket findBasketWithAllItems(Long basketId) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            BasketRepository basketRepository = session.getMapper(BasketRepository.class);
            return basketRepository.findBasketWithAllItems(basketId);
        }
    }

    @Override
    public void addFoodProductToBasket(Long basketId, Long foodProductId) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            BasketRepository basketRepository = session.getMapper(BasketRepository.class);
            basketRepository.addFoodProductToBasket(basketId, foodProductId);
        }
    }

    @Override
    public void addNonPerishableProductToBasket(Long basketId, Long nonPerishableProductId) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            BasketRepository basketRepository = session.getMapper(BasketRepository.class);
            basketRepository.addNonPerishableProductToBasket(basketId, nonPerishableProductId);
        }
    }

    @Override
    public void addDiscountedItemToBasket(Long basketId, Long discountedItemId) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            BasketRepository basketRepository = session.getMapper(BasketRepository.class);
            basketRepository.addDiscountedItemToBasket(basketId, discountedItemId);
        }
    }

    @Override
    public void addServiceToBasket(Long basketId, Long serviceId) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            BasketRepository basketRepository = session.getMapper(BasketRepository.class);
            basketRepository.addServiceToBasket(basketId, serviceId);
        }
    }

    @Override
    public List<Long> findFoodProductIdsByBasketId(Long basketId) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            BasketRepository basketRepository = session.getMapper(BasketRepository.class);
            return basketRepository.findFoodProductIdsByBasketId(basketId);
        }
    }

    @Override
    public List<Long> findNonPerishableProductIdsByBasketId(Long basketId) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            BasketRepository basketRepository = session.getMapper(BasketRepository.class);
            return basketRepository.findNonPerishableProductIdsByBasketId(basketId);
        }
    }

    @Override
    public List<Long> findDiscountedItemIdsByBasketId(Long basketId) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            BasketRepository basketRepository = session.getMapper(BasketRepository.class);
            return basketRepository.findDiscountedItemIdsByBasketId(basketId);
        }
    }

    @Override
    public List<Long> findServiceIdsByBasketId(Long basketId) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            BasketRepository basketRepository = session.getMapper(BasketRepository.class);
            return basketRepository.findServiceIdsByBasketId(basketId);
        }
    }

}