package com.solvd.onlinemarkettc.domain.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.time.Instant;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class FoodProduct {

    @XmlElement(name = "cost")
    @JsonProperty("cost")
    private double cost;

    @XmlElement(name = "id")
    @JsonProperty("id")
    private Long id;

    @JsonProperty("expirationDate")
    @XmlElement(name = "expirationDate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date expirationDate;

    @XmlElement(name = "name")
    @JsonProperty("name")
    private String name;

    public FoodProduct(double cost, String name, Long id) {
        this.cost = cost;
        this.name = name;
        this.id = id;
        this.expirationDate = Date.from(Instant.now());

    }

    public FoodProduct(double cost, String name) {
        this.cost = cost;
        this.name = name;
        this.expirationDate = Date.from(Instant.now());
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
