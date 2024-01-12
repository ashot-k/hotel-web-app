package com.hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class HotelApplication {
	private static final Logger LOG = LoggerFactory.getLogger(HotelApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}
}
