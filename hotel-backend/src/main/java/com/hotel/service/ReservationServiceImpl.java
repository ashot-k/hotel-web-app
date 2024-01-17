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

import java.util.ArrayList;
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
                new EntityNotFoundException(ExceptionMessages.EntityNotFound(Reservation.class.getSimpleName(), id)));
    }

    @Override
    public Reservation reservationDTOToReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setPerson(personService.getPersonByUsername(reservationDTO.username()));
        reservation.setStart(reservationDTO.start());
        reservation.setEnd(reservationDTO.end());
        reservation.setRoom(roomService.getRoomById(reservationDTO.roomId()));
        return reservation;
    }

    @Override
    public ReservationDTO reservationToReservationDTO(Reservation reservation) {
        return new ReservationDTO(reservation.getId(), reservation.getPerson().getUsername(), reservation.getRoomId(), reservation.getStart(), reservation.getEnd());
    }

    @Override
    public List<ReservationDTO> getAllReservationsDTO() {
        List<Reservation> reservations = reservationRepo.findAll();
        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationDTOS.add(reservationToReservationDTO(reservation));
        }
        return reservationDTOS;
    }

    @Override
    public List<Room> getAvailableRooms(Date start, Date end) {
        return reservationRepo.available(start, end);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepo.findAll();
    }

    @Override
    public Reservation createReservation(ReservationDTO reservationDTO) throws RoomReservedException {
        Long roomId = reservationDTO.roomId();
        String username = reservationDTO.username();
        Date start = reservationDTO.start();
        Date end = reservationDTO.end();
        if (reservationRepo.isRoomReserved(-1L, roomId, start, end))
            throw new RoomReservedException("Room with id: " + roomId + " is already reserved");
        Person person = personService.getPersonByUsername(username);
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
            oldReservation.setPerson(personService.getPersonByUsername(updatedReservation.username()));
            oldReservation.setRoom(roomService.getRoomById(updatedReservation.roomId()));
            return reservationRepo.save(oldReservation);
        }).orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.EntityNotFound(Reservation.class.getSimpleName(), id)));
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
