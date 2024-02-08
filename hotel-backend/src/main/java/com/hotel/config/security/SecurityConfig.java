package com.hotel.config.security;

import com.hotel.entity.user.Person;
import com.hotel.repo.PersonRepository;
import com.hotel.service.PersonServiceImpl;
import com.hotel.utils.ExceptionMessages;
import com.hotel.utils.UserRoles;
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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

                        .requestMatchers("/api/rooms/image/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/rooms").permitAll()
                        .requestMatchers("/api/reservations").hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.EMPLOYEE.name())
                        .requestMatchers("/api/users").hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.EMPLOYEE.name())
                        .requestMatchers("/api/rooms").hasAuthority(UserRoles.ADMIN.name())
                        .requestMatchers(HttpMethod.GET,"/api/reservations/available").permitAll()
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
