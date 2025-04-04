package com.redeemerlives.inventort_service;

import org.springframework.boot.SpringApplication;

public class TestInventortServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(InventoryServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
