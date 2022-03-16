package com.luciana.challenge.mybank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BankNotFoundException extends Exception {
    public BankNotFoundException(String bankName) {
        super(String.format("Bank with name %s not found in the system.", bankName));
    }

    public BankNotFoundException(Long id) {
        super(String.format("Bank with id %s not found in the system.", id));
    }
}
