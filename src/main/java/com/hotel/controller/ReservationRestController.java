package com.hotel.controller;


import com.hotel.dto.ReservationDTO;
import com.hotel.entity.reservation.Reservation;
import com.hotel.exceptions.RoomAlreadyReservedException;
import com.hotel.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{reservationId:\\d+}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long reservationId) {
        return new ResponseEntity<>(reservationService.getReservationById(reservationId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations() {
        return new ResponseEntity<>(reservationService.findAllReservations(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody ReservationDTO reservationDTO) throws RoomAlreadyReservedException {
        return new ResponseEntity<>(reservationService.createReservation(reservationDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{reservationId:\\d+}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long reservationId, @Valid @RequestBody ReservationDTO reservationDTO) throws RoomAlreadyReservedException {
        return new ResponseEntity<>(reservationService.updateReservation(reservationId, reservationDTO), HttpStatus.OK);
    }
    @DeleteMapping("/{reservationId:\\d+}")
    public ResponseEntity<String> removeReservation(@PathVariable String reservationId){
        return new ResponseEntity<>(reservationService.deleteReservation(Long.valueOf(reservationId)), HttpStatus.OK);
    }


}
