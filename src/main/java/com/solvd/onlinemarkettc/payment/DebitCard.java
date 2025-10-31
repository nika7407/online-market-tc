package com.solvd.onlinemarkettc.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.onlinemarkettc.util.Generator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class DebitCard {

    @XmlElement(name = "cardNumber")
    @JsonProperty("cardNumber")
    private Long cardId;
    @XmlElement(name = "active")
    @JsonProperty("active")
    private boolean active;
    @XmlElement(name = "moneyAmount")
    @JsonProperty("moneyAmount")
    private double moneyAmount = 0.0;

    public DebitCard(boolean active, double moneyAmount) {
        this.cardId = Long.valueOf(Generator.numberGenerator());
        this.active = active;
        this.moneyAmount = moneyAmount;
    }

    public DebitCard() {
    }

    public Long getCardNumber() {
        return cardId;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardId = cardNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public void minusMoney(Double amountToMinus) {
        moneyAmount = moneyAmount - amountToMinus;
    }
}
