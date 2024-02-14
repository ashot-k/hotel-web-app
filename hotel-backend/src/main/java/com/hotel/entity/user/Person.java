package com.hotel.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person implements UserDetails {
   /* public static final String PASSWORD_ERROR_MESSAGE = """
            Please enter a password that has: <br>
            At least 5 characters. <br>
            At least one letter. <br>
            At least one number.""";*/

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "person_id", nullable = false)
    private Long id;
    @Column(name = "username", nullable = false, unique = true)
    @NotNull(message = "Enter a username")
    @NotBlank(message = "Enter a valid username")
    private String username;
    @Column(name = "pass", nullable = false, length = 1000)
    @NotBlank(message = "Enter valid password")
    @NotNull(message = "Enter a password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @Valid
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private List<Role> roles;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (Role r : roles) {
            authorityList.add(new SimpleGrantedAuthority(r.getUserRole().name()));
        }
        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
