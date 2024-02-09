package com.hotel.service;

import com.hotel.dto.PersonDTO;
import com.hotel.entity.user.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface PersonService {

    Person getPersonById(Long id);
    PersonDTO getPersonDTOById(Long id);
    Person getPersonByUsername(String username);
    PersonDTO getPersonDTOByUsername(String username);
    Page<PersonDTO> getPeopleDTOByTerm(int pageNo, int pageSize, String term);
    List<Person> getAllPeople();
    Page<PersonDTO> getAllPeopleDTOPageable(int pageNo, int pageSize);
    PersonDTO savePerson(PersonDTO person);
    Person savePerson(Person person);
    PersonDTO updatePerson(Long id, PersonDTO updatedPerson);
    String deletePerson(Long id);
    Person personDTOtoPerson(PersonDTO personDTO);
    PersonDTO PersonToPersonDTO(Person p);

}
