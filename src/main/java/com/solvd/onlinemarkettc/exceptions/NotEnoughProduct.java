package com.solvd.onlinemarkettc.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NotEnoughProduct extends RuntimeException {
    private static final Logger log = LogManager.getLogger(NotEnoughProduct.class);

    public NotEnoughProduct(String message) {
        super(message);
        log.error("current product is out of stock! {}", message);
    }

}
