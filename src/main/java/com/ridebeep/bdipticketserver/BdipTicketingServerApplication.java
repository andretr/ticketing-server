package com.ridebeep.bdipticketserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BdipTicketingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BdipTicketingServerApplication.class, args);
		log.info("Application loaded successfully");
	}

}
