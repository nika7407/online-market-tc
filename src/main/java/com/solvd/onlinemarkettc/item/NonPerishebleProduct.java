package com.solvd.onlinemarkettc.item;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class NonPerishebleProduct {

    @XmlElement(name = "name")
    @JsonProperty("name")
    private String name;
    @XmlElement(name = "cost")
    @JsonProperty("cost")
    private double cost;
    @XmlElement(name = "description")
    @JsonProperty("description")
    private String description = "";

    public NonPerishebleProduct(String name, double cost, String description) {
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    public NonPerishebleProduct() {
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
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
