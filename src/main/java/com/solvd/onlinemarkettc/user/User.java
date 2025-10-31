package com.solvd.onlinemarkettc.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.onlinemarkettc.finantialoperation.Basket;
import com.solvd.onlinemarkettc.payment.DebitCard;
import com.solvd.onlinemarkettc.util.Generator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    @XmlElement(name = "name")
    @JsonProperty("name")
    private String name;
    @XmlElement(name = "id")
    @JsonProperty("id")
    private Long id;
    @XmlElement(name = "debitCard")
    @JsonProperty("debitCard")
    private DebitCard debitCard;
    @XmlElement(name = "basket")
    @JsonProperty("basket")
    private Basket basket;

    public User(String name, DebitCard debitCard, Basket basket) {
        this.name = name;
        this.debitCard = debitCard;
        this.basket = basket;
        this.id = Long.valueOf(Generator.numberGenerator());
    }

    public User() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }

    public void setDebitCard(DebitCard debitCard) {
        this.debitCard = debitCard;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Long getId() {
        return id;
    }
}