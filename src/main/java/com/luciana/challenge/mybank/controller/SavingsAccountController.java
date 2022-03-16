package com.luciana.challenge.mybank.controller;

public class SavingsAccountController extends BillController {

    public SavingsAccountController(ClientController client) {
        super(client);
    }

    @Override
    public void printExtract() {
        System.out.println("=== Extract Savings Account ===");
        super.printCommonInfors();
    }
}
