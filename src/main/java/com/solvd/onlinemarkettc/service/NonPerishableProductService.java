package com.solvd.onlinemarkettc.service;

import com.solvd.onlinemarkettc.domain.item.NonPerishebleProduct;

import java.util.List;

public interface NonPerishableProductService {

    Long createNonPerishableProduct(NonPerishebleProduct product);

    NonPerishebleProduct getNonPerishableProductById(Long id);

    NonPerishebleProduct getNonPerishableProductByName(String name);

    List<NonPerishebleProduct> getAllNonPerishableProducts();

    Long updateNonPerishableProduct(NonPerishebleProduct product);

    void deleteNonPerishableProduct(Long id);

    boolean nonPerishableProductExists(Long id);

    boolean nonPerishableProductExistsByName(String name);
}