package com.example.eshopbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class EShopBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EShopBackendApplication.class, args);
	}

}
