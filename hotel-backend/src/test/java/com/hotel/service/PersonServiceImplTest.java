package com.hotel.service;

import com.hotel.entity.user.Person;
import com.hotel.repo.PersonRepository;
import com.hotel.utils.ExceptionMessages;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonServiceImplTest {

     PersonRepository personRepository;

    @BeforeEach
    void setupService(){
        personRepository = mock(PersonRepository.class);
    }
    @Test
    void getPersonById() {
        Long id = 5000000L;
        Person person = new Person(4L, "ashot", "56469e73", null);
        when(personRepository.findById(id)).thenReturn(Optional.of(person));
        Person result = personRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ExceptionMessages.EntityNotFound(Person.class.getSimpleName(), id)));

        Person expectedPerson = new Person(5L, "afkeoasfo", "acvkoe", null);
        assertEquals(expectedPerson, result);
    }
}