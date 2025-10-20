package com.solvd.onlinemarkettc.payment;

import com.solvd.onlinemarkettc.Util.Generator;
import org.apache.logging.log4j.util.PropertySource;

public class DebitCard {

    private String cardNumber;
    private boolean active;
    private double moneyAmount = 0.0;

    public DebitCard(boolean active, double moneyAmount) {
        this.cardNumber = Generator.numberGenerator();
        this.active = active;
        this.moneyAmount = moneyAmount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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
    public void minusMoney(Double amountToMinus){
       moneyAmount = moneyAmount - amountToMinus;
    }
}
