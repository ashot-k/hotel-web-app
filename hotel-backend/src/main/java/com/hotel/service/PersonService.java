package com.hotel.service;

import com.hotel.dto.PersonDTO;
import com.hotel.entity.user.Person;
import org.springframework.data.domain.Page;

import java.util.List;


public interface PersonService {

    Person getPersonById(Long id);

    Person getPersonByUsername(String username);

    List<Person> getAllPeople();

    Person personDTOtoPerson(PersonDTO personDTO);

    PersonDTO PersonToPersonDTO(Person p);

    Page<PersonDTO> getAllPeople(int pageNo, int pageSize);

    PersonDTO getPersonDTOById(Long id);

    PersonDTO savePerson(PersonDTO person);

    PersonDTO updatePerson(Long id, PersonDTO updatedPerson);

    String deletePerson(Long id);
}
