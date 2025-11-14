package com.solvd.onlinemarkettc.service;

import com.solvd.onlinemarkettc.domain.item.FoodProduct;

import java.util.List;
import java.util.Optional;

public interface FoodProductService {

    FoodProduct createFoodProduct(FoodProduct product);

    Optional<FoodProduct> getFoodProductById(Long id);

    Optional<FoodProduct> getFoodProductByName(String name);

    List<FoodProduct> getAllFoodProducts();

    FoodProduct updateFoodProduct(FoodProduct product);

    void deleteFoodProduct(Long id);

    boolean foodProductExists(Long id);

    boolean foodProductExistsByName(String name);
}