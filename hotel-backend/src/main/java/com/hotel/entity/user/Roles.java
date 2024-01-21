package com.hotel.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotel.utils.UserRoles;
import jakarta.persistence.*;

@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @JsonIgnore
    private Long id;
    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;

    @Column(name = "role")
    @Enumerated
    private UserRoles role;

    public Roles() {
    }

    public Roles(Person person, UserRoles role) {
        this.person = person;
        this.role = role;
    }

    public Roles(Person person) {
        this.person = person;
        this.role = UserRoles.CLIENT;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }
}
