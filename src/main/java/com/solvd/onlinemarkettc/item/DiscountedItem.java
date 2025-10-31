package com.solvd.onlinemarkettc.item;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class DiscountedItem {

    @XmlElement(name = "name")
    @JsonProperty("name")
    private String name;
    @XmlElement(name = "id")
    @JsonProperty("id")
    private Long id;
    @XmlElement(name = "cost")
    @JsonProperty("cost")
    private double cost;
    @XmlElement(name = "description")
    @JsonProperty("description")
    private String description = "";

    public DiscountedItem(String name, double cost, String description) {
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    public DiscountedItem() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
