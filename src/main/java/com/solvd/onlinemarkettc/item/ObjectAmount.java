package com.solvd.onlinemarkettc.item;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({FoodProduct.class, NonPerishebleProduct.class})
public class ObjectAmount<K> {

    @XmlElement
    @JsonProperty("amount")
    private int amount = 0;

    @XmlElements({
            @XmlElement(name = "FoodProduct", type = FoodProduct.class),
            @XmlElement(name = "NonPerishebleProduct", type = NonPerishebleProduct.class)
    })
    @JsonProperty("object")
    @JsonAlias({"foodProduct", "nonPerishebleProduct"})
    private K object;

    public ObjectAmount() {
    }

    public ObjectAmount(K object, int amount) {
        this.object = object;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void decrementAmount() {
        amount--;
    }

    public K getObject() {
        return object;
    }

    public void setObject(K object) {
        this.object = object;
    }
}