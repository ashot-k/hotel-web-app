package com.hotel.service;

import com.hotel.dto.ReservationDTO;
import com.hotel.entity.reservation.Reservation;
import com.hotel.entity.room.Room;
import com.hotel.entity.user.Person;
import com.hotel.exceptions.RoomReservedException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReservationService {
    Reservation reservationDTOToReservation(ReservationDTO reservationDTO);
    ReservationDTO reservationToReservationDTO(Reservation reservation);


    List<Room> getAvailableRooms(LocalDate start, LocalDate end);
    Reservation getReservationById(Long id);
    List<Reservation> getAllReservations();
    List<ReservationDTO> getAllReservationsDTO();
    Reservation createReservation(ReservationDTO reservationDTO) throws RoomReservedException;
    String deleteReservation(Long id);
    Reservation updateReservation(Long id, ReservationDTO updatedReservation) throws RoomReservedException;

    void deleteAllReservations(Person p);
}
