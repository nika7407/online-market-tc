package com.solvd.onlinemarkettc.service.implementation;

import com.solvd.onlinemarkettc.domain.item.FoodProduct;
import com.solvd.onlinemarkettc.persistence.repository.FoodProductRepository;
import com.solvd.onlinemarkettc.service.interfaces.FoodProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class FoodProductServiceImpl implements FoodProductService {

    private static final Logger log = LogManager.getLogger(FoodProductServiceImpl.class);
    private final FoodProductRepository foodProductRepository;

    public FoodProductServiceImpl() {
        this.foodProductRepository = new FoodProductRepository();
    }

    public FoodProduct createFoodProduct(FoodProduct product) {
        log.info("create product: {}", product.getName());
        return foodProductRepository.save(product);
    }

    public Optional<FoodProduct> getFoodProductById(Long id) {
        log.debug("find product id: {}", id);
        return foodProductRepository.findById(id);
    }

    public Optional<FoodProduct> getFoodProductByName(String name) {
        log.debug("find product name: {}", name);
        return foodProductRepository.findByName(name);
    }

    public List<FoodProduct> getAllFoodProducts() {
        log.debug("get all products");
        return foodProductRepository.findAll();
    }

    public FoodProduct updateFoodProduct(FoodProduct product) {
        log.info("update product id: {}", product.getId());
        return foodProductRepository.update(product);
    }

    public void deleteFoodProduct(Long id) {
        log.info("delete product id: {}", id);
        foodProductRepository.deleteById(id);
    }

    public boolean foodProductExists(Long id) {
        return foodProductRepository.findById(id).isPresent();
    }

    public boolean foodProductExistsByName(String name) {
        return foodProductRepository.findByName(name).isPresent();
    }
}