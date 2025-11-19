package com.solvd.onlinemarkettc.service;

import com.solvd.onlinemarkettc.domain.item.FoodProduct;

import java.util.List;

public interface FoodProductService {

    Long createFoodProduct(FoodProduct product);

    FoodProduct getFoodProductById(Long id);

    FoodProduct getFoodProductByName(String name);

    List<FoodProduct> getAllFoodProducts();

    Long updateFoodProduct(FoodProduct product);

    void deleteFoodProduct(Long id);

    boolean foodProductExists(Long id);

    boolean foodProductExistsByName(String name);
}