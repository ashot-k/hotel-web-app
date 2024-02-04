package com.hotel.config.security;

import com.hotel.entity.user.Person;
import com.hotel.repo.PersonRepository;
import com.hotel.utils.ExceptionMessages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthConfig {
    PersonRepository personRepo;

    public AuthConfig(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> personRepo.findByUsername(username)
                .orElseThrow(()-> new EntityNotFoundException(ExceptionMessages.EntityNotFound(Person.class.getSimpleName(), username)));
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
