package com.solvd.onlinemarkettc.finantialoperation;

import com.solvd.onlinemarkettc.item.FoodProduct;
import com.solvd.onlinemarkettc.item.NonPerishebleProduct;
import com.solvd.onlinemarkettc.item.ObjectAmount;

import java.util.ArrayList;

public class OnlineShop {

    private ArrayList<ObjectAmount<FoodProduct>> foodProducts;
    private ArrayList<ObjectAmount<NonPerishebleProduct>> nonFoodProduct;

    public OnlineShop(ArrayList<ObjectAmount<FoodProduct>> foodProducts, ArrayList<ObjectAmount<NonPerishebleProduct>> nonFoodProduct) {
        this.foodProducts = foodProducts;
        this.nonFoodProduct = nonFoodProduct;
    }

    public ArrayList<ObjectAmount<FoodProduct>> getFoodProducts() {
        return foodProducts;
    }

    public void setFoodProducts(ArrayList<ObjectAmount<FoodProduct>> foodProducts) {
        this.foodProducts = foodProducts;
    }

    public ArrayList<ObjectAmount<NonPerishebleProduct>> getNonFoodProduct() {
        return nonFoodProduct;
    }

    public void setNonFoodProduct(ArrayList<ObjectAmount<NonPerishebleProduct>> nonFoodProduct) {
        this.nonFoodProduct = nonFoodProduct;
    }

    public boolean addToBasket(FoodProduct foodProduct, Basket basket) {
        return foodProducts.stream()
                .filter(objectAmount -> objectAmount.getObject().equals(foodProduct) && objectAmount.getAmount() > 0)
                .findFirst()
                .map(objectAmount -> {
                    basket.addFoodProduct(foodProduct);
                    objectAmount.setAmount(objectAmount.getAmount() - 1);
                    return true;
                })
                .orElse(false);
    }
}
