package com.hotel.config.security;

import com.hotel.entity.user.Person;
import com.hotel.repo.PersonRepository;
import com.hotel.service.PersonServiceImpl;
import com.hotel.utils.ExceptionMessages;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    JwtAuthenticationFilter jwtAuthFilter;
    AuthenticationProvider authProvider;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authProvider = authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            try {
                requests
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/reservations/available").permitAll()
                        .requestMatchers("/api/rooms/image/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/reservations").authenticated()
                        .requestMatchers("/api/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and().authenticationProvider(authProvider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        http.csrf().disable();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}
