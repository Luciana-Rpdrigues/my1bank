package com.luciana.challenge.mybank.controller;

import lombok.Data;

import java.util.List;

@Data
public class BankController {

    private String name;
    private List<BillController> bills;

}
