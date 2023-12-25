package com.hotel.service;

import com.hotel.entity.reservation.Reservation;
import com.hotel.entity.user.Person;

import java.util.List;

public interface ReservationService {

    Reservation getReservationById(Long id);
    List<Reservation> findAllReservations();
    Reservation createReservation(Reservation reservation);
    String deleteReservation(Long id);
    Reservation updateReservation(Long id, Reservation updatedReservation);
}
