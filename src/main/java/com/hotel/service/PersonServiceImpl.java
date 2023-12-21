package com.hotel.service;

import com.hotel.entity.user.Address;
import com.hotel.entity.user.Person;
import com.hotel.entity.user.Roles;
import com.hotel.repo.PersonRepository;
import com.hotel.utils.UserRoles;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    PersonRepository personRepo;

    public PersonServiceImpl(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public Person getPersonById(Long id) {
        Optional<Person> p = personRepo.findById(id);
        return p.orElse(null);
    }

    @Override
    public List<Person> findAllPeople() {
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
            oldPerson.setUsername(updatedPerson.getUsername());
            oldPerson.setPassword(updatedPerson.getPassword());
            Address oldAddress = oldPerson.getAddress();
            Address newAddress = updatedPerson.getAddress();
            oldAddress.setCountry(newAddress.getCountry());
            oldAddress.setEmail(newAddress.getEmail());
            oldAddress.setStreet(newAddress.getStreet());
            oldAddress.setStreet2(newAddress.getStreet2());
            oldAddress.setPostalCode(newAddress.getPostalCode());
            oldAddress.setPhoneNumber(newAddress.getPhoneNumber());
            oldPerson.setAddress(oldAddress);
            return personRepo.save(oldPerson);
        }).orElseGet(() -> null);
    }

    @Override
    public String deletePerson(Long id) {
        return personRepo.findById(id).map(p -> {
            personRepo.delete(p);
            return "Deleted user with id " + id;
        }).orElseGet(() -> "Could not find user with id " + id);
    }
}
