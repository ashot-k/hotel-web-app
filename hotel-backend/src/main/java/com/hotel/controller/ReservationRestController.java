package com.hotel.controller;


import com.hotel.dto.ReservationDTO;
import com.hotel.entity.reservation.Reservation;
import com.hotel.entity.room.Room;
import com.hotel.exceptions.RoomReservedException;
import com.hotel.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin({"http://192.168.1.75:3000", "http://localhost:3000", "http://192.168.1.75:8080/api/rooms", "http://192.168.1.75:8080/api/rooms/available"})
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
    public ResponseEntity<Page<ReservationDTO>> getReservations(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return new ResponseEntity<>(reservationService.getAllReservationsDTO(pageNo, pageSize), HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<ReservationDTO>> getReservationsByTerm(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                                      @RequestParam("term") String term) {

        return new ResponseEntity<>(reservationService.getAllReservationsDTOByTerm(pageNo, pageSize, term), HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Room>> getAvailable(@RequestParam("start") String start, @RequestParam("end") String end) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String startFormatted = outputFormat.format(inputFormat.parse(start));
        String endFormatted = outputFormat.format(inputFormat.parse(end));

        LocalDate startDate = LocalDate.parse(startFormatted, outputFormat);
        LocalDate endDate = LocalDate.parse(endFormatted, outputFormat);

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
    public ResponseEntity<String> removeReservation(@PathVariable String reservationId) {
        return new ResponseEntity<>(reservationService.deleteReservation(Long.valueOf(reservationId)), HttpStatus.OK);
    }
}
