package com.luciana.challenge.mybank.controller;

public interface IBillController {

    void to_withdraw(double value);
    void deposit(double value);
    void transfer(double value, BillController destinationAccount);
    void printExtract();
}
