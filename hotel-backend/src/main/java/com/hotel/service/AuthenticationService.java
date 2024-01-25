package com.hotel.service;

import com.hotel.config.security.JwtService;
import com.hotel.controller.AuthenticationRequest;
import com.hotel.controller.AuthenticationResponse;
import com.hotel.dto.PersonDTO;
import com.hotel.entity.user.Person;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    PersonService personService;
    JwtService jwtService;
    AuthenticationManager authManager;

    public AuthenticationService(PersonService personService, JwtService jwtService, AuthenticationManager authManager) {
        this.personService = personService;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    public AuthenticationResponse register(PersonDTO personDTO) {
        Person person = personService.personDTOtoPerson(personService.savePerson(personDTO));
        var jwtToken = jwtService.generateToken(person);
        return new AuthenticationResponse(jwtToken);
    }
    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
       authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
       Person person = personService.getPersonByUsername(authRequest.getUsername());
       var jwtToken = jwtService.generateToken(person);
       return new AuthenticationResponse(jwtToken);
    }

}
