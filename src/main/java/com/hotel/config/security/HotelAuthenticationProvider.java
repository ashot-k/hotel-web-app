package com.hotel.config.security;

import com.hotel.entity.user.Person;
import com.hotel.entity.user.Roles;
import com.hotel.service.PersonService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.in;

@Component
public class HotelAuthenticationProvider implements AuthenticationProvider {

    PersonService personService;

    PasswordEncoder passwordEncoder;

    public HotelAuthenticationProvider(PersonService personService, PasswordEncoder passwordEncoder) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<GrantedAuthority> authorities = null;
        Person person = null;

        try {
            person = personService.getPersonByUsername(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User: " + username + " not found");
        }
        if (passwordEncoder.matches(password, person.getPassword())) {
            authorities = new ArrayList<>();
            for (Roles r : person.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(r.getRole()));
            }
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        } else
            throw new BadCredentialsException("Invalid Password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
