package com.hotel.controller;

import com.hotel.entity.user.Address;
import com.hotel.entity.user.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PersonRestControllerTest {

    @Autowired
    private PersonRestController controller;
    @Test
    void getPeople() {
    }

    @Test
    void createPerson() {
        Person c = new Person("testname", "testpass123@A", null);
        c.setAddress(new Address(c));
        Person savedPerson = controller.personService.savePerson(c);
        System.out.println(savedPerson);
        assertThat(c.getAddress()).isNotNull();
        assertThat(c.getAddress().getPerson()).isNotNull();
    }

    @Test
    void deletePerson() {
    }
}