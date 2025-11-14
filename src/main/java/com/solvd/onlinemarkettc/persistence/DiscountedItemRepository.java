package com.solvd.onlinemarkettc.persistence;

import com.solvd.onlinemarkettc.domain.item.DiscountedItem;

import java.util.List;
import java.util.Optional;

public interface DiscountedItemRepository {

    Optional<DiscountedItem> findById(Long id);

    Optional<DiscountedItem> findByName(String name);

    List<DiscountedItem> findAll();

    DiscountedItem save(DiscountedItem discountedItem);

    void deleteById(Long id);

    DiscountedItem update(DiscountedItem discountedItem);

}