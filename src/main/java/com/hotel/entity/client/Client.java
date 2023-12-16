package com.hotel.entity.client;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Client {
    public static final String PASSWORD_ERROR_MESSAGE = """
            Please enter a password that has: <br>
            At least 5 characters. <br>
            At least one letter. <br>
            At least one number.""";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    @Column(name = "pass", nullable = false, length = 1000)
    @NotBlank(message = "Enter valid password")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{5,}$"
            , message = PASSWORD_ERROR_MESSAGE)
    private String password;

















    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
