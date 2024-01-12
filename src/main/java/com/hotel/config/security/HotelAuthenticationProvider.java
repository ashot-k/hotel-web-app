package com.hotel.config.security;

import com.hotel.entity.user.Person;
import com.hotel.service.PersonService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class HotelAuthenticationProvider implements AuthenticationProvider {

    PersonService personService;

    PasswordEncoder passwordEncoder;

    public HotelAuthenticationProvider(PersonService personService, PasswordEncoder passwordEncoder) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<GrantedAuthority> authorities = null;
        Person person = null;

        try {
            person = personService.getPersonByUsername(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with name " + username + " not found");
        }
        if (passwordEncoder.matches(password, person.getPassword())) {
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(person.getRoles().getRole()));
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        } else
            throw new BadCredentialsException("Invalid Password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
