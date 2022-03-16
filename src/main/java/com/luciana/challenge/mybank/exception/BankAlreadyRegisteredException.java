package com.luciana.challenge.mybank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BankAlreadyRegisteredException extends  Exception{

    public BankAlreadyRegisteredException(String bankName) {
        super(String.format("Bank with name %s not found in the system.", bankName));
    }
}
