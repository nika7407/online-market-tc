package com.solvd.onlinemarkettc.service.interfaces;

import com.solvd.onlinemarkettc.domain.item.NonPerishebleProduct;

import java.util.List;
import java.util.Optional;

public interface NonPerishableProductService {
    NonPerishebleProduct createNonPerishableProduct(NonPerishebleProduct product);
    Optional<NonPerishebleProduct> getNonPerishableProductById(Long id);
    Optional<NonPerishebleProduct> getNonPerishableProductByName(String name);
    List<NonPerishebleProduct> getAllNonPerishableProducts();
    NonPerishebleProduct updateNonPerishableProduct(NonPerishebleProduct product);
    void deleteNonPerishableProduct(Long id);
    boolean nonPerishableProductExists(Long id);
    boolean nonPerishableProductExistsByName(String name);
}