package com.hotel.service;


import com.hotel.dto.ReservationDTO;
import com.hotel.entity.reservation.Reservation;
import com.hotel.entity.room.Room;
import com.hotel.entity.user.Person;
import com.hotel.repo.ReservationRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    ReservationRepo reservationRepo;
    PersonService personService;
    RoomService roomService;

    public ReservationServiceImpl(ReservationRepo reservationRepo, PersonService personService, RoomService roomService) {
        this.reservationRepo = reservationRepo;
        this.personService = personService;
        this.roomService = roomService;
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
    public Reservation createReservation(ReservationDTO reservationDTO) {
        //todo check if start > end
        if(reservationRepo.isRoomBooked(reservationDTO.roomId(), reservationDTO.start(), reservationDTO.end()))
            return null;
        Person person = personService.getPersonById(reservationDTO.personId());
        Room room = roomService.getRoomById(reservationDTO.roomId());
        Reservation reservation = new Reservation(person, room, reservationDTO.start(), reservationDTO.end());
        return reservationRepo.save(reservation);
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
