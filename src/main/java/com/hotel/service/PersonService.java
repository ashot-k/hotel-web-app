package com.hotel.service;

import com.hotel.entity.user.Person;

import java.util.List;


public interface PersonService {

    Person getPersonById(Long id);
    List<Person> getAllPeople();
    Person savePerson(Person person);
    String deletePerson(Long id);
    Person updatePerson(Long id, Person updatedPerson);
}
