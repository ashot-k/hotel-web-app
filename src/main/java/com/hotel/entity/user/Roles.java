package com.hotel.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.hotel.utils.UserRoles;
import jakarta.persistence.*;

@Entity
@JsonIgnoreType
public class Roles {
    @Id
    @Column(name = "person_id")
    private Long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "role")
    private String role = UserRoles.CLIENT.toString();

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
