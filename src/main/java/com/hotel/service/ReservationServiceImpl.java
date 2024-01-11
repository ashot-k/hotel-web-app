package com.hotel.service;


import com.hotel.dto.ReservationDTO;
import com.hotel.entity.reservation.Reservation;
import com.hotel.entity.room.Room;
import com.hotel.entity.user.Person;
import com.hotel.exceptions.RoomReservedException;
import com.hotel.repo.ReservationRepo;
import com.hotel.utils.ExceptionMessages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        return reservationRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException(ExceptionMessages.EntityNotFoundMessage(Reservation.class.getSimpleName(), id)));
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
    public Reservation createReservation(ReservationDTO reservationDTO) throws RoomReservedException {
        Long roomId = reservationDTO.roomId();
        Long personId = reservationDTO.personId();
        Date start = reservationDTO.start();
        Date end = reservationDTO.end();
        if (reservationRepo.isRoomReserved(-1L, roomId, start, end))
            throw new RoomReservedException("Room with id: " + roomId + " is already reserved");
        Person person = personService.getPersonById(personId);
        Room room = roomService.getRoomById(roomId);
        Reservation reservation = new Reservation(person, room, start, end);
        return reservationRepo.save(reservation);
    }

    @Override
    public Reservation updateReservation(Long id, ReservationDTO updatedReservation) {
        return reservationRepo.findById(id).map(oldReservation -> {
            oldReservation.setStart(updatedReservation.start());
            oldReservation.setEnd(updatedReservation.end());
            if (reservationRepo.isRoomReserved(oldReservation.getId(), updatedReservation.roomId(), updatedReservation.start(), updatedReservation.end())) {
                try {
                    throw new RoomReservedException("Room with id: " + updatedReservation.roomId() + " is already reserved");
                } catch (RoomReservedException e) {
                    throw new RuntimeException(e);
                }
            }
            oldReservation.setPerson(personService.getPersonById(updatedReservation.personId()));
            oldReservation.setRoom(roomService.getRoomById(updatedReservation.roomId()));
            return reservationRepo.save(oldReservation);
        }).orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.EntityNotFoundMessage(Reservation.class.getSimpleName(), id)));
    }

    @Override
    public String deleteReservation(Long id) {
        reservationRepo.delete(this.getReservationById(id));
        return "Deleted Reservation with id: " + id;
    }

    @Override
    public void deleteAllReservations(Person p) {
        reservationRepo.deleteByPerson(p);
    }
}
