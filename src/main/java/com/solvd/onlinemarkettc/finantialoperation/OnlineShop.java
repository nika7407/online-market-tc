package com.solvd.onlinemarkettc.finantialoperation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.onlinemarkettc.exceptions.UserNotFoundException;
import com.solvd.onlinemarkettc.item.FoodProduct;
import com.solvd.onlinemarkettc.item.NonPerishebleProduct;
import com.solvd.onlinemarkettc.item.ObjectAmount;
import com.solvd.onlinemarkettc.user.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "OnlineShop")
@XmlAccessorType(XmlAccessType.FIELD)
public class OnlineShop {

    @XmlElementWrapper(name = "foodProducts")
    @XmlElement(name = "ObjectAmount")
    @JsonProperty("foodProducts")
    private ArrayList<ObjectAmount<FoodProduct>> foodProducts;

    @XmlElementWrapper(name = "nonFoodProduct")
    @XmlElement(name = "ObjectAmount")
    @JsonProperty("nonFoodProducts")
    private ArrayList<ObjectAmount<NonPerishebleProduct>> nonFoodProduct;

    @XmlElementWrapper(name = "users")
    @XmlElement(name = "User")
    @JsonProperty("users")
    private ArrayList<User> users;

    public OnlineShop() {
        this.foodProducts = new ArrayList<>();
        this.nonFoodProduct = new ArrayList<>();
        this.users = new ArrayList<>();
    }


    public ArrayList<ObjectAmount<FoodProduct>> getFoodProducts() {
        return foodProducts;
    }

    public ArrayList<ObjectAmount<NonPerishebleProduct>> getNonFoodProduct() {
        return nonFoodProduct;
    }

    public List<User> getUsers() {
        return users;
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

    public void addFoodProduct(FoodProduct product, int amount) {
        foodProducts.add(new ObjectAmount<>(product, amount));
    }

    public void addNonFoodProduct(NonPerishebleProduct product, int amount) {
        nonFoodProduct.add(new ObjectAmount<>(product, amount));
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUser(String id) {
        return users.stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst().orElseThrow(() -> new UserNotFoundException("user noot found"));
    }
}