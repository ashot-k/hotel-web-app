package com.hotel.service;

import com.hotel.entity.user.Person;
import com.hotel.exceptions.UsernameAlreadyExistsException;

import java.util.List;


public interface PersonService {

    Person getPersonById(Long id);
    Person getPersonByUsername(String username);
    List<Person> getAllPeople();
    Person savePerson(Person person);
    String deletePerson(Long id);
    Person updatePerson(Long id, Person updatedPerson);
}
