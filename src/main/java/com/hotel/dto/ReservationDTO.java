package com.hotel.dto;

import com.hotel.exceptions.InvalidDatesException;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ReservationDTO(@NotNull(message = "Please enter personId") Long personId,
                             @NotNull(message = "Please enter roomId") Long roomId,
                             @NotNull(message = "Invalid Date for start") @FutureOrPresent(message = "Invalid Date for start") Date start,
                             @NotNull(message = "Invalid Date for end") @Future(message = "Invalid Date for end") Date end) {
    public ReservationDTO {
        if (start.compareTo(end) > 0) {
            try {
                throw new InvalidDatesException("Invalid Dates");
            } catch (InvalidDatesException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
