package com.hotel.service;


import com.hotel.entity.reservation.Reservation;
import com.hotel.repo.ReservationRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    ReservationRepo reservationRepo;

    public ReservationServiceImpl(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    @Override
    public Reservation getReservationById(Long id) {
        return null;
    }

    @Override
    public List<Reservation> findAllReservations() {
        return reservationRepo.findAll();
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        return null;
    }

    @Override
    public String deleteReservation(Long id) {
        reservationRepo.delete(this.getReservationById(id));
        return "Deleted Reservation with id: " + id;
    }

    @Override
    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        return null;
    }
}
