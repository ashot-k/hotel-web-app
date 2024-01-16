package com.hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class HotelApplication {
	private static final Logger LOG = LoggerFactory.getLogger(HotelApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://192.168.1.75:3000", "http://localhost:3000")
						.allowedHeaders("X-Total-Pages")
						.exposedHeaders("X-Total-Pages");
			}
		};
	}
}
