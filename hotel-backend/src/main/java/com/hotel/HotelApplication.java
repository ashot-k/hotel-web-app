package com.hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
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
                registry.addMapping("/**")
                        .allowedOrigins("http://192.168.1.64:3000", "http://localhost:3000", "http://192.168.64:8080")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("X-Total-Pages", "Content-Type", "Authorization")
                        .exposedHeaders("X-Total-Pages");
            }
        };
    }
    /*
    @Value("${server.port}")
    private int serverPort;
    @Value("${react.port}")
    private int reactPort;
    @Value("${server.address}")
    private String serverAddress;
    */

}
