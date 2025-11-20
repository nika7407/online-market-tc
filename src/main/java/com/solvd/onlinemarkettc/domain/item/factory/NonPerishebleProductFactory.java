package com.solvd.onlinemarkettc.domain.item.factory;

import com.solvd.onlinemarkettc.domain.item.NonPerishebleProduct;

public class NonPerishebleProductFactory {

    public static NonPerishebleProduct create(String name, double cost, String description) {
        return new NonPerishebleProduct(name, cost, description);
    }

}