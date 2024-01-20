package com.hotel.service;

import com.hotel.entity.room.Room;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RoomService {

    Room getRoomById(Long id);
    Page<Room> getAllRooms(int pageNo, int pageSize);
    Room saveRoom(Room room);
    String deleteRoom(Long id);
    Room updateRoom(Long id, Room updatedRoom);
    List<Room> getAllRooms();
    Page<Room> getRoomsByTerm(int pageNo, int pageSize, String term);
    ByteArrayResource getRoomImage(String RoomId) throws IOException;
}
