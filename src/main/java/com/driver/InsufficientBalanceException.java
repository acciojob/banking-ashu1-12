package com.driver;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String insu) {
        super(insu);
    }
}
