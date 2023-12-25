package com.hotel.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    public static final String PASSWORD_ERROR_MESSAGE = """
            Please enter a password that has: <br>
            At least 5 characters. <br>
            At least one letter. <br>
            At least one number.""";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "person_id", nullable = false)
    private Long id;
    @Column(name = "username", nullable = false)
    @NotNull
    private String username;
    @Column(name = "pass", nullable = false, length = 1000)
    @NotBlank(message = "Enter valid password")
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{5,}$"
            , message = PASSWORD_ERROR_MESSAGE)
    private String password;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @Valid
    private Address address;
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Roles roles;

    public Person() {

    }

    public Person(String username, String password, Address address) {
        this.username = username;
        this.password = password;
        this.address = address;
    }

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Person(Long id, String username, String password, Address address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}
