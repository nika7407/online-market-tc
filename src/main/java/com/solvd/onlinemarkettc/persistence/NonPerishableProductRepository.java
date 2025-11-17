package com.solvd.onlinemarkettc.persistence;

import com.solvd.onlinemarkettc.domain.item.NonPerishebleProduct;

import java.util.List;
import java.util.Optional;

public interface NonPerishableProductRepository {

    Optional<NonPerishebleProduct> findById(Long id);

    Optional<NonPerishebleProduct> findByName(String name);

    List<NonPerishebleProduct> findAll();

    Long save(NonPerishebleProduct nonPerishableProduct);

    void deleteById(Long id);

    Long update(NonPerishebleProduct nonPerishableProduct);

}