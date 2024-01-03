package com.hotel.controller;


import com.hotel.dto.ReservationDTO;
import com.hotel.entity.reservation.Reservation;
import com.hotel.entity.user.Person;
import com.hotel.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService){
        this.reservationService = reservationService;
    }
    @PostMapping("/create-reservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDTO reservationDTO){
        return new ResponseEntity<>(reservationService.createReservation(reservationDTO), HttpStatus.CREATED);
    }


}
