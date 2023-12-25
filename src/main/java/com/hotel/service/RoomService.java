package com.hotel.service;

import com.hotel.entity.room.Room;

import java.util.List;

public interface RoomService {
    Room getRoomById(Long id);
    List<Room> findAllRooms();
    Room saveRoom(Room room);
    String deleteRoom(Long id);
    Room updateRoom(Long id, Room updatedRoom);
}
