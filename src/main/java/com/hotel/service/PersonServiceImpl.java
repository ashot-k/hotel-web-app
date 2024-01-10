package com.hotel.service;

import com.hotel.entity.user.Address;
import com.hotel.entity.user.Person;
import com.hotel.entity.user.Roles;
import com.hotel.repo.PersonRepository;
import com.hotel.repo.ReservationRepo;
import com.hotel.utils.ExceptionMessages;
import com.hotel.utils.UserRoles;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    PersonRepository personRepo;

    ReservationRepo reservationRepo;

    public PersonServiceImpl(PersonRepository personRepo, ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
        this.personRepo = personRepo;
    }

    @Override
    public Person getPersonById(Long id) {
        return personRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(
                ExceptionMessages.EntityNotFoundMessage(Person.class.getSimpleName(), id)));
    }

    @Override
    public List<Person> getAllPeople() {
        return personRepo.findAll();
    }

    @Override
    public Person savePerson(Person person) {
        if (person.getId() != null && personRepo.findById(person.getId()).isPresent())
            return null;
        else
            person.setRoles(new Roles(person, UserRoles.CLIENT));
        if (person.getAddress() != null)
            person.getAddress().setPerson(person);
        else {
            person.setAddress(new Address());
            person.getAddress().setPerson(person);
        }
        return personRepo.save(person);
    }

    @Override
    public Person updatePerson(Long id, Person updatedPerson) {
        return personRepo.findById(id).map(oldPerson -> {
            updatedPerson.setId(oldPerson.getId());
            oldPerson = updatedPerson;
            oldPerson.getAddress().setPerson(oldPerson);
            oldPerson.getAddress().setId(oldPerson.getId());
            return personRepo.save(oldPerson);
        }).orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.EntityNotFoundMessage(Person.class.getSimpleName(), id)));
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
