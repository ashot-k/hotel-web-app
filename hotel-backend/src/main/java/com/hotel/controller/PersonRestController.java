package com.hotel.controller;

import com.hotel.dto.PersonDTO;
import com.hotel.entity.user.Person;
import com.hotel.service.PersonService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Secured("ADMIN")
@RequestMapping("/api/users")
@CrossOrigin({"http://192.168.1.75:3000", "http://localhost:3000", "http://192.168.1.75:8080/api/users"})

public class PersonRestController {
    PersonService personService;
    private static final Logger LOG = LoggerFactory.getLogger(PersonRestController.class);

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{personId:\\d+}")
    public ResponseEntity<PersonDTO> getUser(@PathVariable Long personId) {
        return new ResponseEntity<>(personService.getPersonById(personId), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PersonDTO>> getUsers(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        Page<PersonDTO> page = personService.getAllPeople(pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Pages", String.valueOf(page.getTotalPages()));

        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createUser(@Valid @RequestBody PersonDTO person) {
        return new ResponseEntity<>(personService.savePerson(person), HttpStatus.CREATED);
    }

    @PutMapping("/{personId:\\d+}")
    public ResponseEntity<PersonDTO> updateUser(@Valid @RequestBody PersonDTO updatedPerson, @PathVariable Long personId) {
        return new ResponseEntity<>(personService.updatePerson(personId, updatedPerson), HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<String> deleteUser(@PathVariable String personId) {
        return new ResponseEntity<>(personService.deletePerson(Long.parseLong(personId)), HttpStatus.OK);
    }
}