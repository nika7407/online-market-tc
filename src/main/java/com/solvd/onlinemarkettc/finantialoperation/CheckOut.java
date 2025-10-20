package com.solvd.onlinemarkettc.finantialoperation;

import com.solvd.onlinemarkettc.exceptions.InsufficientFunds;
import com.solvd.onlinemarkettc.payment.DebitCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckOut {

    private static final Logger log = LogManager.getLogger(CheckOut.class);

    public static void checkoutBasket(DebitCard paymentMethod, Basket basket) {
        double cost = basket.getSumCost();
        if (cost > paymentMethod.getMoneyAmount()) {
            throw new InsufficientFunds("not enugh funds for " + basket.getId());
        }
        paymentMethod.minusMoney(cost);
        log.info("payment finished for {}", basket.getId());
    }
}
