package com.hotel.controller;


import com.hotel.entity.reservation.Reservation;
import com.hotel.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService){
        this.reservationService = reservationService;
    }
    @PostMapping("/create-reservation")
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody Reservation reservation){
        System.out.println();






        return new ResponseEntity<>(reservationService.createReservation(reservation), HttpStatus.CREATED);
    }


}
