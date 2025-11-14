package com.solvd.onlinemarkettc.persistence;

import com.solvd.onlinemarkettc.domain.finantialoperation.Basket;

import java.util.List;
import java.util.Optional;

public interface BasketRepository {

    Optional<Basket> findById(Long id);

    List<Basket> findAll();

    Basket save(Basket basket);

    void deleteById(Long id);

    Basket update(Basket basket);

    Basket findBasketWithAllItems(Long basketId);
}