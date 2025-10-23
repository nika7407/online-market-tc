package com.solvd.onlinemarkettc.finantialoperation;

import com.jayway.jsonpath.JsonPath;
import com.solvd.onlinemarkettc.item.FoodProduct;
import com.solvd.onlinemarkettc.item.NonPerishebleProduct;
import com.solvd.onlinemarkettc.item.ObjectAmount;
import com.solvd.onlinemarkettc.payment.DebitCard;
import com.solvd.onlinemarkettc.util.Jparser;
import com.solvd.onlinemarkettc.util.Xparser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws JAXBException, IOException {
        System.out.println("Program started");
        System.out.println("=== System.out ===");
        log.info("=== Logger INFO ===");
        log.error("=== Logger ERROR ===");
        log.debug("=== Logger DEBUG ===");
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

        OnlineShop shop = new OnlineShop();
        log.info("online shop launched!");
        Basket basket = new Basket();
        basket.addFoodProduct(apple);
        DebitCard debitCard1 = new DebitCard(true, 100000);

        CheckOut.checkoutBasket(debitCard1, basket);

        Xparser parser = new Xparser();
        OnlineShop xmlShop = parser.unmarshal("src/main/java/resources/hierarchy.xml");
        log.info("xml hierarchy parsed!");

        log.info(xmlShop.getFoodProducts().getFirst().getObject());

        Jparser jparser = new Jparser();
        OnlineShop jshop = jparser.parse("src/main/java/resources/hierarchy.json");

        String json = Files.readString(Paths.get("src/main/java/resources/hierarchy.json"));

        String name = JsonPath.read(json, "$.foodProducts[0].foodProduct.name");
        log.info("First food product: {}", name);

        Double cost = JsonPath.read(json, "$.nonFoodProducts[0].nonPerishebleProduct.cost");
        log.info("First non-food cost: {}", cost);

        String firstName = JsonPath.read(json, "$.users[0].name");
        log.info("First user name: {}", firstName);

        Double money = JsonPath.read(json, "$.users[0].debitCard.moneyAmount");
        log.info("First user money: {}", money);

        String basketId = JsonPath.read(json, "$.users[0].basket.basketId");
        log.info("First basket ID: {}", basketId);

        System.out.println("ended");


    }
}