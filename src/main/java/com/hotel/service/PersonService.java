package com.hotel.service;

import com.hotel.entity.user.Person;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PersonService {

    Person findPerson(Long id);
    List<Person> findAllPeople();
    Person savePerson(Person person);
    Person deletePerson(Long id);
}
