package com.solvd.onlinemarkettc.domain.delivery;

import com.solvd.onlinemarkettc.domain.finantialoperation.Basket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Delivery {

    private static final Logger log = LogManager.getLogger(Delivery.class);
    private AddressInterface addressInterface;

    private Basket basket;

    public Delivery(AddressInterface addressInterface, Basket basket) {
        this.addressInterface = addressInterface;
        this.basket = basket;
    }

    public AddressInterface getAddressInterface() {
        return addressInterface;
    }

    public void setAddressInterface(AddressInterface addressInterface) {
        this.addressInterface = addressInterface;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public void Deliver(){
        log.info("delivery has been made to {}", addressInterface.getDeliveryAddress());
    }
}
