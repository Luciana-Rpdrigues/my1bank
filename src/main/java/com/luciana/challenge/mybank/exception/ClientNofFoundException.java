package com.luciana.challenge.mybank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNofFoundException extends Exception {
    public ClientNofFoundException(Long id) { super("Client not found with ID " + id); }
}
