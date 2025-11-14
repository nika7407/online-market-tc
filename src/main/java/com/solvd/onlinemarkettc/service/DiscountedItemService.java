package com.solvd.onlinemarkettc.service;

import com.solvd.onlinemarkettc.domain.item.DiscountedItem;

import java.util.List;
import java.util.Optional;

public interface DiscountedItemService {

    DiscountedItem createDiscountedItem(DiscountedItem item);

    Optional<DiscountedItem> getDiscountedItemById(Long id);

    Optional<DiscountedItem> getDiscountedItemByName(String name);

    List<DiscountedItem> getAllDiscountedItems();

    DiscountedItem updateDiscountedItem(DiscountedItem item);

    void deleteDiscountedItem(Long id);

    boolean discountedItemExists(Long id);

    boolean discountedItemExistsByName(String name);
}