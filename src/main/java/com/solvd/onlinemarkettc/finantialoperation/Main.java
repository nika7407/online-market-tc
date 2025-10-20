package com.solvd.onlinemarkettc.finantialoperation;

import com.solvd.onlinemarkettc.item.FoodProduct;
import com.solvd.onlinemarkettc.item.NonPerishebleProduct;
import com.solvd.onlinemarkettc.item.ObjectAmount;
import com.solvd.onlinemarkettc.payment.DebitCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        FoodProduct apple = new FoodProduct(0.5, "apple");
        FoodProduct banana = new FoodProduct(0.3, "banana");

        NonPerishebleProduct soap = new NonPerishebleProduct("soap", 1.2, "");
        NonPerishebleProduct shampoo = new NonPerishebleProduct("shampoo", 3.5, "");

        ArrayList<ObjectAmount<FoodProduct>> foodList = new ArrayList<>();
        foodList.add(new ObjectAmount<>(apple, 10));
        foodList.add(new ObjectAmount<>(banana, 20));

        ArrayList<ObjectAmount<NonPerishebleProduct>> nonFoodList = new ArrayList<>();
        nonFoodList.add(new ObjectAmount<>(soap, 5));
        nonFoodList.add(new ObjectAmount<>(shampoo, 7));

        OnlineShop shop = new OnlineShop(foodList, nonFoodList);
        log.info("online shop launched!");
        Basket basket = new Basket();
        basket.addFoodProduct(apple);
        DebitCard debitCard1 = new DebitCard(true, 100000);

        CheckOut.checkoutBasket(debitCard1, basket);
    }
}