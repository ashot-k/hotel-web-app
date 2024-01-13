package com.hotel.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.Arrays;
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
    @Column(name = "username", nullable = false, unique = true)
    @NotNull
    private String username;
    @Column(name = "pass", nullable = false, length = 1000)
    @NotBlank(message = "Enter valid password")
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{5,}$"
            , message = PASSWORD_ERROR_MESSAGE)

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    private String password;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @Valid
    private Address address;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private List<Roles> roles;
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (id == null || obj == null || getClass() != obj.getClass())
            return false;
        Person that = (Person) obj;
        return id.equals(that.id);
    }
    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
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

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}
