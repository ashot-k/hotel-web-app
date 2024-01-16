package com.hotel.service;

import com.hotel.entity.room.Room;
import com.hotel.repo.RoomRepository;
import com.hotel.utils.ExceptionMessages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    RoomRepository roomRepo;

    public RoomServiceImpl(RoomRepository roomRepo) {
        this.roomRepo = roomRepo;
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
    public String deleteRoom(Long id) {
            roomRepo.delete(this.getRoomById(id));
            return "Deleted Room with id " + id;
    }
}
