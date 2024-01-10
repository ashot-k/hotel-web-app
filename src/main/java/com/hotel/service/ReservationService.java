package com.hotel.service;

import com.hotel.dto.ReservationDTO;
import com.hotel.entity.reservation.Reservation;
import com.hotel.entity.user.Person;
import com.hotel.exceptions.RoomAlreadyReservedException;

import java.util.List;

public interface ReservationService {

    Reservation getReservationById(Long id);
    List<Reservation> findAllReservations();
    Reservation createReservation(Reservation reservation);
    Reservation createReservation(ReservationDTO reservationDTO) throws RoomAlreadyReservedException;
    String deleteReservation(Long id);
    Reservation updateReservation(Long id, ReservationDTO updatedReservation) throws RoomAlreadyReservedException;

    void deleteAllReservations(Person p);
}
