package com.luciana.challenge.mybank.controller;

public class CurrentAccountController extends BillController {

    public CurrentAccountController(ClientController client) {
        super(client);
    }

    @Override
    public void printExtract() {
        System.out.println("=== Extract Current Account ===");
        super.printCommonInfors();
    }
}
