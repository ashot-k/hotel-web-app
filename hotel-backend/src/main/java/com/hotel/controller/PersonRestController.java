package com.hotel.controller;

import com.hotel.entity.user.Person;
import com.hotel.service.PersonService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
//@Secured("ADMIN")
@RequestMapping("/api/users")
public class PersonRestController {
    PersonService personService;
    private static final Logger LOG = LoggerFactory.getLogger(PersonRestController.class);

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{personId:\\d+}")
    public ResponseEntity<Person> getUser(@PathVariable Long personId) {
        return new ResponseEntity<>(personService.getPersonById(personId), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Person>> getUsers() {
        return new ResponseEntity<>(personService.getAllPeople(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> createUser(@Valid @RequestBody Person person) {
        return new ResponseEntity<>(personService.savePerson(person), HttpStatus.CREATED);
    }

    @PutMapping("/{personId:\\d+}")
    public ResponseEntity<Person> updateUser(@Valid @RequestBody Person updatedPerson, @PathVariable Long personId) {
        return new ResponseEntity<>(personService.updatePerson(personId, updatedPerson), HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<String> deleteUser(@PathVariable String personId) {
        return new ResponseEntity<>(personService.deletePerson(Long.parseLong(personId)), HttpStatus.OK);
    }
}