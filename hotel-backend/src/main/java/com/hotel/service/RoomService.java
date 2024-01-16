package com.hotel.service;

import com.hotel.entity.room.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoomService {
    Room getRoomById(Long id);
   // List<Room> getAllRooms();
    Page<Room> getAllRooms(int pageNo, int pageSize);

    Room saveRoom(Room room);
    String deleteRoom(Long id);
    Room updateRoom(Long id, Room updatedRoom);
}
