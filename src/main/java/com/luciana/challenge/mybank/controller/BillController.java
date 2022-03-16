package com.luciana.challenge.mybank.controller;

public abstract class BillController implements IBillController {

    private static final int Schedule_Standard = 1;
    private static  int SEQUENCIAL = 1;

    protected int schedule;
    protected int number;
    protected double balance;
    protected ClientController client;

    protected void printCommonInfors() {
        System.out.println(String.format("Holder: %s", this.client.getClientService()));
        System.out.println(String.format("Schedule: %d", this.schedule));
        System.out.println(String.format("Number: %d", this.number));
        System.out.println(String.format("Balance: %.2f", this.balance));
    }

    public BillController(ClientController client) {
        this.schedule = Schedule_Standard;
        this.number = SEQUENCIAL++;
        this.client = client;
    }

    @Override
    public void to_withdraw(double value) {
        balance -= value;
    }

    @Override
    public void deposit(double value) {
        balance += value;
    }

    @Override
    public void transfer(double value, BillController destinationAccount) {
        this.to_withdraw(value);
        destinationAccount.deposit(value);
    }

    public int getSchedule() {
        return schedule;
    }

    public int getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }
}
