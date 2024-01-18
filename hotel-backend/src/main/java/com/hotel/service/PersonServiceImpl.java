package com.hotel.service;

import com.hotel.dto.PersonDTO;
import com.hotel.entity.user.Address;
import com.hotel.entity.user.Person;
import com.hotel.entity.user.Roles;
import com.hotel.exceptions.UsernameAlreadyExistsException;
import com.hotel.repo.PersonRepository;
import com.hotel.repo.ReservationRepo;
import com.hotel.utils.ExceptionMessages;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    PersonRepository personRepo;

    ReservationRepo reservationRepo;

    PasswordEncoder bCryptEncoder;

    public PersonServiceImpl(PersonRepository personRepo, ReservationRepo reservationRepo, PasswordEncoder bCryptEncoder) {
        this.reservationRepo = reservationRepo;
        this.personRepo = personRepo;
        this.bCryptEncoder = bCryptEncoder;
    }


    @Override
    public PersonDTO getPersonDTOById(Long id) {
        return personRepo.findById(id).map(this::PersonToPersonDTO)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.EntityNotFound(Person.class.getSimpleName(), id)));
    }

    @Override
    public List<PersonDTO> getPersonDTOsByUsername(String username) {
        List<Person> people = personRepo.findByUsernameTerm("%" + username +"%");
        List<PersonDTO> personDTOList = new ArrayList<>();
        people.forEach((person)->{
            personDTOList.add(PersonToPersonDTO(person));
        });

        return personDTOList;
    }

    @Override
    public Person getPersonById(Long id) {
        return personRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.EntityNotFound(Person.class.getSimpleName(), id)));
    }


    @Override
    public Person getPersonByUsername(String username) {
        return personRepo.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(
                ExceptionMessages.EntityNotFound(Person.class.getSimpleName(), username)));
    }


    @Override
    public List<Person> getAllPeople() {
        return personRepo.findAll();
    }

    @Override
    public Page<PersonDTO> getAllPeople(int pageNo, int pageSize) {
        return personRepo.findAll(PageRequest.of(pageNo, pageSize)).map(
                (this::PersonToPersonDTO)
        );
    }

    @Override
    public Person personDTOtoPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setId(personDTO.id());
        person.setUsername(personDTO.username());
        person.setPassword(personDTO.password());

        Address address = new Address();
        address.setPhoneNumber(personDTO.phoneNumber());
        address.setEmail(personDTO.email());
        address.setPostalCode(personDTO.postalCode());
        address.setCountry(personDTO.country());
        address.setStreet(personDTO.street());
        address.setStreet2(personDTO.street2());
        person.setAddress(address);
        return person;
    }

    @Override
    public PersonDTO PersonToPersonDTO(Person p) {
        Address a = p.getAddress();
        return new PersonDTO(p.getId(), p.getUsername(), p.getPassword()
                , a.getEmail(), a.getCountry(), a.getPostalCode(),
                a.getStreet(), a.getStreet2(), a.getPhoneNumber());
    }


    @Override
    public PersonDTO savePerson(PersonDTO personDTO) {
        Person person = personDTOtoPerson(personDTO);
        if (person.getId() != null || personRepo.findByUsername(person.getUsername()).isPresent()) {
            try {
                throw new UsernameAlreadyExistsException(ExceptionMessages.AlreadyExists(Person.class.getSimpleName(), person.getUsername()));
            } catch (UsernameAlreadyExistsException e) {
                throw new RuntimeException(e);
            }
        } else
            person.setRoles(List.of(new Roles(person)));
        if (person.getAddress() == null)
            person.setAddress(new Address());
        person.getAddress().setPerson(person);
        person.setPassword(bCryptEncoder.encode(person.getPassword()));
        return PersonToPersonDTO(personRepo.save(person));
    }

    @Override
    public PersonDTO updatePerson(Long id, PersonDTO updatedPersonDTO) {
        Person updatedPerson = personDTOtoPerson(updatedPersonDTO);
        return personRepo.findById(id).map(oldPerson -> {
            updatedPerson.setId(oldPerson.getId());
            oldPerson = updatedPerson;
            oldPerson.getAddress().setPerson(oldPerson);
            oldPerson.getAddress().setId(oldPerson.getId());
            return PersonToPersonDTO(personRepo.save(oldPerson));
        }).orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.EntityNotFound(Person.class.getSimpleName(), id)));
    }

    @Override
    @Transactional
    public String deletePerson(Long id) {
        return personRepo.findById(id).map(p -> {
            reservationRepo.deleteByPerson(p);
            personRepo.delete(p);
            return "Deleted user with id " + id;
        }).orElseThrow(() -> new EntityNotFoundException("Person with id: " + id + " not found"));
    }
}
