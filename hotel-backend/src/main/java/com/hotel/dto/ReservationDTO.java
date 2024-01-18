package com.hotel.dto;

import com.hotel.exceptions.InvalidDatesException;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record ReservationDTO(
        Long id,
        @NotNull(message = "Please enter username") String username,
                             @NotNull(message = "Please enter roomId") Long roomId,
                             @NotNull(message = "Invalid Date for start") @FutureOrPresent(message = "Invalid Date for start") LocalDate start,
                             @NotNull(message = "Invalid Date for end") @Future(message = "Invalid Date for end") LocalDate end) {
    public ReservationDTO {
        if (start != null && end != null)
            if (start.isAfter(end)) {
                try {
                    throw new InvalidDatesException("Invalid Dates");
                } catch (InvalidDatesException e) {
                    throw new RuntimeException(e);
                }
            }
    }
}
