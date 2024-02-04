package com.hotel.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotel.utils.UserRoles;
import jakarta.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @JsonIgnore
    private Long id;
    @Column(name = "role")
    @Enumerated
    private UserRoles userRole;

    public Role(UserRoles role) {
        this.userRole = role;
    }

    public Role() {
        this.userRole = UserRoles.CLIENT;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRoles getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoles userRole) {
        this.userRole = userRole;
    }
}
