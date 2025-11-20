package com.solvd.onlinemarkettc.domain.item.factory;

import com.solvd.onlinemarkettc.domain.item.FoodProduct;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class FoodProductFactory {

    public  FoodProduct create(double cost, String name, Date expirationDate) {
        FoodProduct product = new FoodProduct(cost, name);
        product.setExpirationDate(Objects.requireNonNullElseGet(expirationDate,
                () -> new Date(1900, Calendar.FEBRUARY, 1)));
        return product;
    }
}