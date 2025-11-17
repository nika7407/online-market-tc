package com.solvd.onlinemarkettc.persistence;

import com.solvd.onlinemarkettc.domain.item.FoodProduct;

import java.util.List;
import java.util.Optional;

public interface FoodProductRepository {
    Optional<FoodProduct> findById(Long id);

    Optional<FoodProduct> findByName(String name);

    List<FoodProduct> findAll();

    Long save(FoodProduct foodProduct);

    void deleteById(Long id);

    Long update(FoodProduct foodProduct);
}