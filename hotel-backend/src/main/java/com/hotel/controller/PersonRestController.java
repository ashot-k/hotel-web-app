package com.hotel.controller;

import com.hotel.dto.PersonDTO;
import com.hotel.service.PersonService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class PersonRestController {
    PersonService personService;
    private static final Logger LOG = LoggerFactory.getLogger(PersonRestController.class);

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{personId:\\d+}")
    public ResponseEntity<PersonDTO> getUser(@PathVariable Long personId) {
        return new ResponseEntity<>(personService.getPersonDTOById(personId), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PersonDTO>> getUserByTerm(@RequestParam("term") String term,
                                                         @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return new ResponseEntity<>(personService.getPeopleDTOByTerm(pageNo, pageSize, term), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<PersonDTO>> getUsers(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return new ResponseEntity<>(personService.getAllPeopleDTOPageable(pageNo, pageSize), HttpStatus.OK);
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