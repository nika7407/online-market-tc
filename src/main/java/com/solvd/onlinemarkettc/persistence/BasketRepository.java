package com.solvd.onlinemarkettc.persistence;

import com.solvd.onlinemarkettc.domain.finantialoperation.Basket;

import java.util.List;
import java.util.Optional;

public interface BasketRepository {

    Optional<Basket> findById(Long id);

    List<Basket> findAll();

    Long save(Basket basket);

    void deleteById(Long id);

    Long update(Basket basket);

    Basket findBasketWithAllItems(Long basketId);

    // i added mtm cos they are nedeed
    void addFoodProductToBasket(Long basketId, Long foodProductId);

    void addNonPerishableProductToBasket(Long basketId, Long nonPerishableProductId);

    void addDiscountedItemToBasket(Long basketId, Long discountedItemId);

    void addServiceToBasket(Long basketId, Long serviceId);

    List<Long> findFoodProductIdsByBasketId(Long basketId);

    List<Long> findNonPerishableProductIdsByBasketId(Long basketId);

    List<Long> findDiscountedItemIdsByBasketId(Long basketId);

    List<Long> findServiceIdsByBasketId(Long basketId);
}