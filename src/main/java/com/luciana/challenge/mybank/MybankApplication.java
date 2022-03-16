package com.luciana.challenge.mybank;

import com.luciana.challenge.mybank.controller.BillController;
import com.luciana.challenge.mybank.controller.ClientController;
import com.luciana.challenge.mybank.controller.CurrentAccountController;
import com.luciana.challenge.mybank.controller.SavingsAccountController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybankApplication {

	public static void main(String[] args) {
		ClientController luciana = new ClientController();
		luciana.getClientService();

		BillController cc = new CurrentAccountController(luciana);
		BillController cp = new SavingsAccountController(luciana);

		cc.deposit(500);

		cc.transfer(100, cp);
		cp.transfer(30, cc);

		cc.printExtract();
		cp.printExtract();
	}
}
