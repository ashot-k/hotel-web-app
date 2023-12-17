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
public class PersonServiceImpl implements PersonService{
    
    PersonRepository personRepo;
    public PersonServiceImpl(PersonRepository personRepo){
        this.personRepo = personRepo;
    }
    @Override
    public Person findPerson(Long id) {
        Optional<Person> p =  personRepo.findById(id);
        return p.orElse(null);
    }

    @Override
    public List<Person> findAllPeople() {
        return personRepo.findAll();
    }

    @Override
    public Person savePerson(Person person) {
        person.setRoles(new Roles(person, UserRoles.CLIENT));
        if (person.getAddress() != null)
            person.getAddress().setPerson(person);
        else
            person.setAddress(new Address());
        return personRepo.save(person);
    }

    @Override
    public Person deletePerson(Long id) {
        return null;
    }
}
