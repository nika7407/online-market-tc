package com.solvd.onlinemarkettc.service.impl;

import com.solvd.onlinemarkettc.domain.item.FoodProduct;
import com.solvd.onlinemarkettc.persistence.impl.FoodProductRepositoryImpl;
import com.solvd.onlinemarkettc.service.FoodProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class FoodProductServiceImpl implements FoodProductService {

    private static final Logger log = LogManager.getLogger(FoodProductServiceImpl.class);
    private final FoodProductRepositoryImpl foodProductRepository;

    public FoodProductServiceImpl() {
        this.foodProductRepository = new FoodProductRepositoryImpl();
    }

    @Override
    public FoodProduct createFoodProduct(FoodProduct product) {
        log.info("create product: {}", product.getName());
        return foodProductRepository.save(product);
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
    public FoodProduct updateFoodProduct(FoodProduct product) {
        log.info("update product id: {}", product.getId());
        return foodProductRepository.update(product);
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