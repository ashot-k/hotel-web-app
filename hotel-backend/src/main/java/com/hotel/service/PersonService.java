package com.hotel.service;

import com.hotel.entity.user.Person;
import org.springframework.data.domain.Page;

import java.util.List;


public interface PersonService {

    Person getPersonById(Long id);
    Person getPersonByUsername(String username);
    List<Person> getAllPeople();

    Page<Person> getAllPeople(int pageNo, int pageSize);

    Person savePerson(Person person);
    String deletePerson(Long id);
    Person updatePerson(Long id, Person updatedPerson);
}
