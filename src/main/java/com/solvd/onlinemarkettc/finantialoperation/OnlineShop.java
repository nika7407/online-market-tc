package com.solvd.onlinemarkettc.finantialoperation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.onlinemarkettc.exceptions.UserNotFoundException;
import com.solvd.onlinemarkettc.item.DiscountedItem;
import com.solvd.onlinemarkettc.item.FoodProduct;
import com.solvd.onlinemarkettc.item.NonPerishebleProduct;
import com.solvd.onlinemarkettc.item.Service;
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
    @XmlElement(name = "FoodProduct")
    @JsonProperty("foodProducts")
    private List<FoodProduct> foodProducts;

    @XmlElementWrapper(name = "nonFoodProduct")
    @XmlElement(name = "NonPerishebleProduct")
    @JsonProperty("nonFoodProducts")
    private List<NonPerishebleProduct> nonFoodProduct;

    @XmlElementWrapper(name = "users")
    @XmlElement(name = "User")
    @JsonProperty("users")
    private List<User> users;

    @XmlElementWrapper(name = "services")
    @XmlElement(name = "Service")
    @JsonProperty("services")
    private List<Service> serviceList;

    @XmlElementWrapper(name = "discountedItems")
    @XmlElement(name = "DiscountedItem")
    @JsonProperty("discountedItems")
    private List<DiscountedItem> discountList;

    public OnlineShop() {
        this.foodProducts = new ArrayList<>();
        this.nonFoodProduct = new ArrayList<>();
        this.users = new ArrayList<>();
        this.serviceList = new ArrayList<>();
        this.discountList = new ArrayList<>();
    }


    public List<FoodProduct> getFoodProducts() {
        return foodProducts;
    }

    public List<NonPerishebleProduct> getNonFoodProduct() {
        return nonFoodProduct;
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean addToBasket(FoodProduct foodProduct, Basket basket) {
        return foodProducts.stream()
                .filter(food -> food.equals(foodProduct))
                .findFirst()
                .map(objectAmount -> {
                    basket.addFoodProduct(foodProduct);
                    return true;
                })
                .orElse(false);
    }

    public void addFoodProduct(FoodProduct product, int amount) {
        foodProducts.add(product);
    }

    public void addNonFoodProduct(NonPerishebleProduct product, int amount) {
        nonFoodProduct.add(product);
    }

    public void setFoodProducts(List<FoodProduct> foodProducts) {
        this.foodProducts = foodProducts;
    }

    public void setNonFoodProduct(List<NonPerishebleProduct> nonFoodProduct) {
        this.nonFoodProduct = nonFoodProduct;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUser(String id) {
        return users.stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst().orElseThrow(() -> new UserNotFoundException("user noot found"));
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public List<DiscountedItem> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<DiscountedItem> discountList) {
        this.discountList = discountList;
    }
}