package com.hotel.config.security;

/*
import com.hotel.entity.user.Person;
import com.hotel.entity.user.Roles;
import com.hotel.service.PersonService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelUserDetails implements UserDetailsService {
    PersonService personService;

    public HotelUserDetails(PersonService personService) {
        this.personService = personService;
    }


   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String name, password = null;
        List<GrantedAuthority> authorities = null;

        Person person = null;
        try {
            person = personService.getPersonByUsername(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with name " + username + " not found");
        }
        name = person.getUsername();
        password = person.getPassword();
        authorities = new ArrayList<>();
        for (Roles role : person.getRoles())
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        return new User(name, password, authorities);

    }
}
*/
