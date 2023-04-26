package com.ridebeep.bdipticketingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BdipTicketingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BdipTicketingServerApplication.class, args);
		System.out.println("Application loaded successfully");
	}

}
