package com.solvd.onlinemarkettc.service.impl;

import com.solvd.onlinemarkettc.domain.item.FoodProduct;
import com.solvd.onlinemarkettc.persistence.mybatisimpl.FoodProductRepositoryMybatisImpl;
import com.solvd.onlinemarkettc.service.FoodProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class FoodProductServiceImpl implements FoodProductService {

    private static final Logger log = LogManager.getLogger(FoodProductServiceImpl.class);
    //private final FoodProductRepositoryImpl foodProductRepository;
    private final FoodProductRepositoryMybatisImpl foodProductRepository;

    public FoodProductServiceImpl() {
        this.foodProductRepository = new FoodProductRepositoryMybatisImpl();
    }

    @Override
    public Long createFoodProduct(FoodProduct product) {
        log.info("create product: {}", product.getName());
        Long id = foodProductRepository.save(product);
        return product.getId();
    }

    @Override
    public Optional<FoodProduct> getFoodProductById(Long id) {
        log.debug("find product id: {}", id);
        return foodProductRepository.findById(id);
    }

    @Override
    public Optional<FoodProduct> getFoodProductByName(String name) {
        log.debug("find product name: {}", name);
        return foodProductRepository.findByName(name);
    }

    @Override
    public List<FoodProduct> getAllFoodProducts() {
        log.debug("get all products");
        return foodProductRepository.findAll();
    }

    @Override
    public Long updateFoodProduct(FoodProduct product) {
        log.info("update product id: {}", product.getId());
        foodProductRepository.update(product);
        return product.getId();
    }

    @Override
    public void deleteFoodProduct(Long id) {
        log.info("delete product id: {}", id);
        foodProductRepository.deleteById(id);
    }

    @Override
    public boolean foodProductExists(Long id) {
        return foodProductRepository.findById(id).isPresent();
    }

    @Override
    public boolean foodProductExistsByName(String name) {
        return foodProductRepository.findByName(name).isPresent();
    }
}