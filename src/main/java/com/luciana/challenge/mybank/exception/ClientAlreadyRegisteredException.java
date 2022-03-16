package com.luciana.challenge.mybank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientAlreadyRegisteredException extends Exception{

    public ClientAlreadyRegisteredException(String bankName) {
        super(String.format("Client with name %s not found in the system.", bankName));
    }
}
