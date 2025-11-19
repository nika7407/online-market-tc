package com.solvd.onlinemarkettc.service;

import com.solvd.onlinemarkettc.domain.finantialoperation.Basket;

import java.util.List;

public interface BasketService {

    Basket createBasketWithItems(Basket basket);

    Basket getBasketById(Long id);

    List<Basket> getAllBaskets();

    Long updateBasket(Basket basket);

    void deleteBasket(Long id);

    boolean basketExists(Long id);

}