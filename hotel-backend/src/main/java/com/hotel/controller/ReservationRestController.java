package com.hotel.controller;


import com.hotel.dto.ReservationDTO;
import com.hotel.entity.reservation.Reservation;
import com.hotel.entity.room.Room;
import com.hotel.exceptions.RoomReservedException;
import com.hotel.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.form.InputTag;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    ReservationService reservationService ;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{reservationId:\\d+}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long reservationId) {
        return new ResponseEntity<>(reservationService.getReservationById(reservationId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        return new ResponseEntity<>(reservationService.getAllReservationsDTO(), HttpStatus.OK);
    }
    @GetMapping("/available")
    public ResponseEntity<List<Room>> getAvailable(@RequestParam("start") String start, @RequestParam("end") String end){
        //return new ResponseEntity<>(reservationService.getAvailableRooms());
       DateFormat inputFormatter = new SimpleDateFormat("dd-MM-yyyy");
       Date startDate,endDate;
        try {
            startDate = inputFormatter.parse(start);
            endDate = inputFormatter.parse(end);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(reservationService.getAvailableRooms(startDate, endDate), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody ReservationDTO reservationDTO) throws RoomReservedException {
        return new ResponseEntity<>(reservationService.createReservation(reservationDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{reservationId:\\d+}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long reservationId, @Valid @RequestBody ReservationDTO reservationDTO) throws RoomReservedException {
        return new ResponseEntity<>(reservationService.updateReservation(reservationId, reservationDTO), HttpStatus.OK);
    }
    @DeleteMapping("/{reservationId:\\d+}")
    public ResponseEntity<String> removeReservation(@PathVariable String reservationId){
        return new ResponseEntity<>(reservationService.deleteReservation(Long.valueOf(reservationId)), HttpStatus.OK);
    }
}
