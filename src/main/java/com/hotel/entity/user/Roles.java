package com.hotel.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
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
    //@MapsId
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;

    @Column(name = "role")
    private String role;

    public Roles() {
    }

    public Roles(Person person, String role) {
        this.person = person;
        this.role = role;
    }

    public Roles(Person person, UserRoles role) {
        this.person = person;
        this.role = role.toString();
    }

    public Roles(Person person) {
        this.person = person;
        this.role = UserRoles.CLIENT.toString();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
