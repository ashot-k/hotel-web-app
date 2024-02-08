package com.hotel;

import com.hotel.dto.PersonDTO;
import com.hotel.entity.user.Address;
import com.hotel.entity.user.Person;
import com.hotel.entity.user.Role;
import com.hotel.repo.PersonRepository;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@SpringBootApplication
public class HotelApplication implements CommandLineRunner{
    private static final Logger LOG = LoggerFactory.getLogger(HotelApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HotelApplication.class, args);
    }

    PersonRepository personRepository;
    PasswordEncoder bCryptEncoder;
    public HotelApplication(PersonRepository personRepository, PasswordEncoder bCryptEncoder){
        this.personRepository = personRepository;
        this.bCryptEncoder = bCryptEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if(personRepository.findByUsername("admin").isEmpty()) {
            Person admin = new Person();
            admin.setUsername("admin");
            admin.setPassword(bCryptEncoder.encode("admin"));
            admin.setRoles(new ArrayList<>());
            admin.getRoles().add(new Role(UserRoles.ADMIN));
            admin.setAddress(new Address("admin@gmaill.com", "undefined", "12345", "street", "", "1234567890"));
            admin.getAddress().setPerson(admin);
            personRepository.save(admin);
        }
        if(personRepository.findByUsername("employee").isEmpty()) {
            Person employee = new Person();
            employee.setUsername("employee");
            employee.setPassword(bCryptEncoder.encode("employee"));
            employee.setRoles(new ArrayList<>());
            employee.getRoles().add(new Role(UserRoles.EMPLOYEE));
            employee.setAddress(new Address("employee@gmaill.com", "undefined", "12345", "street", "", "1234567890"));
            employee.getAddress().setPerson(employee);
            personRepository.save(employee);
        }
        if(personRepository.findByUsername("client").isEmpty()) {
            Person client = new Person();
            client.setUsername("client");
            client.setPassword(bCryptEncoder.encode("client"));
            client.setRoles(new ArrayList<>());
            client.getRoles().add(new Role(UserRoles.CLIENT));
            client.setAddress(new Address("client@gmaill.com", "undefined", "12345", "street", "", "1234567890"));
            client.getAddress().setPerson(client);
            personRepository.save(client);
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
