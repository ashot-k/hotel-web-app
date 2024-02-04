package com.hotel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PersonDTO(
        Long id,
        @NotNull String username,
        @NotBlank(message = "Enter valid password") @NotNull
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) String password,
        @NotNull(message = "Please input an email address")
        @NotBlank(message = "Please enter a valid email")
        String email,
        String country, String postalCode,
        @NotNull(message = "Please input an street address")
        @NotBlank(message = "Please enter a valid street address")
        String street,
        String street2,
        @NotNull(message = "Please input a phone number")
        @NotBlank(message = "Please enter a valid phone number")
        /*@Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$", message = "Invalid phone number format")
      */  String phoneNumber, String role) {


}
