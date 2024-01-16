package com.hotel.service;

import com.hotel.dto.PersonDTO;
import com.hotel.entity.user.Person;
import org.springframework.data.domain.Page;

import java.util.List;


public interface PersonService {

    PersonDTO getPersonById(Long id);
    Person getPersonByUsername(String username);
    List<Person> getAllPeople();

    Page<PersonDTO> getAllPeople(int pageNo, int pageSize);

    String deletePerson(Long id);

    Person personDTOtoPerson(PersonDTO personDTO);

    PersonDTO PersonToPersonDTO(Person p);

    PersonDTO savePerson(PersonDTO person);

    PersonDTO updatePerson(Long id, PersonDTO updatedPerson);
}
