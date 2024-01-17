package com.hotel.service;

import com.hotel.entity.room.Room;
import com.hotel.repo.ReservationRepo;
import com.hotel.repo.RoomRepository;
import com.hotel.utils.ExceptionMessages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    RoomRepository roomRepo;

    ReservationRepo reservationRepo;

    public RoomServiceImpl(RoomRepository roomRepo, ReservationRepo reservationRepo) {
        this.roomRepo = roomRepo;
        this.reservationRepo = reservationRepo;
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(
                ExceptionMessages.EntityNotFound(Room.class.getSimpleName(), id)));
    }

    @Override
    public Page<Room> getAllRooms(int pageNo, int pageSize) {
        return roomRepo.findAll(PageRequest.of(pageNo, pageSize));
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepo.save(room);
    }

    @Override
    public Room updateRoom(Long id, Room updatedRoom) {
        return roomRepo.findById(id).map(oldRoom -> {
            updatedRoom.setId(oldRoom.getId());
            oldRoom = updatedRoom;
            return roomRepo.save(oldRoom);
        }).orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.EntityNotFound(Room.class.getSimpleName(), id)));
    }


    @Override
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    @Override
    public String deleteRoom(Long id) {
        try {
            roomRepo.delete(this.getRoomById(id));
        } catch (DataIntegrityViolationException e) {

            return "Could not delete room with id " + id + ", room might be reserved";
        }
        return "Deleted Room with id: " + id;
    }
}
