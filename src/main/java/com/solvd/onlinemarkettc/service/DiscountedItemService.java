package com.solvd.onlinemarkettc.service;

import com.solvd.onlinemarkettc.domain.item.DiscountedItem;

import java.util.List;

public interface DiscountedItemService {

    Long createDiscountedItem(DiscountedItem item);

    DiscountedItem getDiscountedItemById(Long id);

    DiscountedItem getDiscountedItemByName(String name);

    List<DiscountedItem> getAllDiscountedItems();

    Long updateDiscountedItem(DiscountedItem item);

    void deleteDiscountedItem(Long id);

    boolean discountedItemExists(Long id);

    boolean discountedItemExistsByName(String name);
}