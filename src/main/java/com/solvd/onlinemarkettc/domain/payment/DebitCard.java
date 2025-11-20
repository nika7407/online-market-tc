package com.solvd.onlinemarkettc.domain.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.onlinemarkettc.domain.util.Generator;

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
    private double moneyAmount;

    private static final EventHolder eventHolder = new EventHolder();

    public DebitCard(boolean active, double moneyAmount) {
        this.cardId = Long.valueOf(Generator.numberGenerator());
        this.active = active;
        this.moneyAmount = moneyAmount;
    }

    public DebitCard() {
    }

    public DebitCard(Long cardId, boolean active, double moneyAmount) {
        this.cardId = cardId;
        this.active = active;
        this.moneyAmount = moneyAmount;
    }

    public Long getCardNumber() {
        return cardId;
    }

    public void setCardNumber(Long cardId) {
        this.cardId = cardId;
    }

    public boolean isActive() {
        return active;
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public static EventHolder getEventHolder() {
        return eventHolder;
    }

    public void setActive(boolean active) {
        boolean old = this.active;
        this.active = active;

        if (!old && active)
            eventHolder.notify(EventType.CARD_ACTIVATED, "activated" + cardId);
        if (old && !active)
            eventHolder.notify(EventType.CARD_BLOCKED, "blocked" + cardId);
    }

    public void setMoneyAmount(double moneyAmount) {
        double old = this.moneyAmount;
        this.moneyAmount = moneyAmount;
        eventHolder.notify(EventType.BALANCE_CHANGED,
                "Balance: " + old + " -> " + moneyAmount);
    }

    public void minusMoney(double amount) {
        double old = this.moneyAmount;
        this.moneyAmount -= amount;
        eventHolder.notify(EventType.BALANCE_CHANGED,
                "Balance: " + old + " -> " + this.moneyAmount);
    }
}
