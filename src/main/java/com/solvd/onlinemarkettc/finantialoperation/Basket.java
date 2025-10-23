package com.solvd.onlinemarkettc.finantialoperation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.onlinemarkettc.item.FoodProduct;
import com.solvd.onlinemarkettc.item.NonPerishebleProduct;
import com.solvd.onlinemarkettc.util.Generator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class Basket {

    @XmlElement(name = "basketId")
    @JsonProperty("basketId")
    private String basketId;

    @XmlElementWrapper(name = "foodProductList")
    @XmlElement(name = "FoodProduct")
    @JsonProperty("foodProductList")
    private ArrayList<FoodProduct> foodProductList = new ArrayList<>();

    @XmlElementWrapper(name = "nonPerishebleProductList")
    @XmlElement(name = "NonPerishebleProduct")
    @JsonProperty("nonPerishebleProductList")
    private ArrayList<NonPerishebleProduct> nonPerishebleProductList = new ArrayList<>();

    private double sumCost = 0.0;
    @JsonProperty("date")
    private Date date;

    public Basket() {
        this.basketId = Generator.numberGenerator();
        this.date = new Date();
    }

    public ArrayList<FoodProduct> getFoodProductList() {
        return foodProductList;
    }

    public void addFoodProduct(FoodProduct foodProduct) {
        foodProductList.add(foodProduct);
        calculateCost();
    }

    public void addProduct(NonPerishebleProduct product) {
        nonPerishebleProductList.add(product);
        calculateCost();
    }

    public ArrayList<NonPerishebleProduct> getNonPerishebleProductList() {
        return nonPerishebleProductList;
    }

    public double getSumCost() {
        return sumCost;
    }

    private void calculateCost() {

        double totalCostOfFood = foodProductList.stream()
                .mapToDouble(FoodProduct::getCost)
                .sum();

        double totalCostOfProduct = nonPerishebleProductList.stream()
                .mapToDouble(NonPerishebleProduct::getCost)
                .sum();

        sumCost = totalCostOfFood + totalCostOfProduct;
    }

    public String getId() {
        return basketId;
    }
}
