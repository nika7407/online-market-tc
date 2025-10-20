package com.solvd.onlinemarkettc.item;

import java.time.LocalDateTime;

public class FoodProduct {

    private double cost;
    private LocalDateTime expirationDate;
    private String name;

    public FoodProduct(double cost, String name) {
        this.cost = cost;
        this.expirationDate = expirationDate;
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
