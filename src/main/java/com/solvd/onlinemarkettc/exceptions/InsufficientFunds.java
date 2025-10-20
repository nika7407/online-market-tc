package com.solvd.onlinemarkettc.exceptions;

public class InsufficientFunds extends RuntimeException {
    public InsufficientFunds(String message) {
        super(message);
    }
}
