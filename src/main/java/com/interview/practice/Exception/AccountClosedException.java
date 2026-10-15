package com.interview.practice.Exception;

public class AccountClosedException extends RuntimeException {
    public AccountClosedException(String message) {
        super(message) ;
    }
}
