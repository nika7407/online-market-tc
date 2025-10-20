package com.solvd.onlinemarkettc.finantialoperation;

import com.solvd.onlinemarkettc.Util.Generator;
import com.solvd.onlinemarkettc.item.FoodProduct;
import com.solvd.onlinemarkettc.item.NonPerishebleProduct;

import java.util.ArrayList;
import java.util.Date;

public class Basket {

    private String basketId;
    private ArrayList<FoodProduct> foodProductList = new ArrayList<>();
    private ArrayList<NonPerishebleProduct> nonPerishebleProductList = new ArrayList<>();
    private double sumCost = 0.0;
    private Date date;

    public Basket() {
        this.basketId = Generator.numberGenerator();
        this.date = new Date();
    }

    public ArrayList<FoodProduct> getFoodProductList() {
        return foodProductList;
    }

    public void addFoodProduct(FoodProduct foodProduct){
        foodProductList.add(foodProduct);
        calculateCost();
    }

    public void addProduct(NonPerishebleProduct product){
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

    public String getId(){
        return basketId;
    }
}
