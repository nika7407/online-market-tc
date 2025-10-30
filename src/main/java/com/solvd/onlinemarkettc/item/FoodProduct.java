package com.solvd.onlinemarkettc.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class FoodProduct {

    @XmlElement(name = "cost")
    @JsonProperty("cost")
    private double cost;

    @XmlElement(name = "productId")
    @JsonProperty("productId")
    private String productId;

    @JsonProperty("expirationDate")
    @XmlElement(name = "expirationDate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date expirationDate;

    @XmlElement(name = "name")
    @JsonProperty("name")
    private String name;

    public FoodProduct(double cost, String name, String productId) {
        this.cost = cost;
        this.name = name;
        this.productId=productId;
    }

    public FoodProduct() {
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
