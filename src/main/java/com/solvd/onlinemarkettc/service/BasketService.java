package com.solvd.onlinemarkettc.service;

import com.solvd.onlinemarkettc.domain.finantialoperation.Basket;

import java.util.List;
import java.util.Optional;

public interface BasketService {

    Basket createBasketWithItems(Basket basket);

    Optional<Basket> getBasketById(Long id);

    List<Basket> getAllBaskets();

    Basket updateBasket(Basket basket);

    void deleteBasket(Long id);

    boolean basketExists(Long id);

}