package com.hotel;

import com.hotel.dto.PersonDTO;
import com.hotel.entity.user.Address;
import com.hotel.entity.user.Person;
import com.hotel.entity.user.Role;
import com.hotel.service.PersonService;
import com.hotel.utils.UserRoles;
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

import java.util.ArrayList;

@SpringBootApplication
public class HotelApplication implements CommandLineRunner{
    private static final Logger LOG = LoggerFactory.getLogger(HotelApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HotelApplication.class, args);
    }

    PersonService personService;
    public HotelApplication(PersonService personService){
        this.personService = personService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(personService.getPersonByUsername("admin") == null) {
            Person admin = new Person();
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setRoles(new ArrayList<>());
            admin.getRoles().add(new Role(UserRoles.ADMIN));
            admin.setAddress(new Address("admin@gmaill.com", "undefined", "12345", "street", "", "1234567890"));
            personService.savePerson(admin);
        }
    }

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.simpleDateFormat("yyyy-MM-dd");
        return builder;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://192.168.1.64:8080", "http://192.168.1.64:3000","http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("X-Total-Pages", "Content-Type", "Authorization")
                        .exposedHeaders("X-Total-Pages");
            }
        };
    }
}
