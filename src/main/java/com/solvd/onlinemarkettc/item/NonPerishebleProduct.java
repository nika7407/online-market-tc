package com.solvd.onlinemarkettc.item;

public class NonPerishebleProduct {

    private String name;
    private double cost;
    private String Description = "";

    public NonPerishebleProduct(String name, double cost, String description) {
        this.name = name;
        this.cost = cost;
        Description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
