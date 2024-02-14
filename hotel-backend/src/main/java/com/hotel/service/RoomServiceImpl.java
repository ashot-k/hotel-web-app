package com.hotel.service;

import com.hotel.entity.room.Room;
import com.hotel.entity.room.RoomType;
import com.hotel.repo.ReservationRepo;
import com.hotel.repo.RoomRepository;
import com.hotel.utils.ExceptionMessages;
import com.hotel.utils.ImageUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
        if (room.getImageUrl() != null) {
            ImageUtils.saveImageToFileSystem(ImageUtils.decodeBase64Image(room.getImageUrl()), ImageUtils.roomImageDirectory, room.getName());
            room.setImageUrl(room.getName());
        }
        return roomRepo.save(room);
    }

    @Override
    public Room updateRoom(Long id, Room updatedRoom) {
        return roomRepo.findById(id).map(oldRoom -> {
            if (updatedRoom.getImageUrl() == null) {
                if (oldRoom.getImageUrl() != null)
                    if (!updatedRoom.getName().equalsIgnoreCase(oldRoom.getName()))
                        ImageUtils.rename(ImageUtils.roomImageDirectory + "/" + oldRoom.getImageUrl(), ImageUtils.roomImageDirectory + "/" + updatedRoom.getName());
            } else {
                if (oldRoom.getImageUrl() != null)
                    ImageUtils.deleteImage(ImageUtils.roomImageDirectory, oldRoom.getImageUrl());
                ImageUtils.saveImageToFileSystem(ImageUtils.decodeBase64Image(updatedRoom.getImageUrl()), ImageUtils.roomImageDirectory, updatedRoom.getName());
            }
            updatedRoom.setImageUrl(updatedRoom.getName());
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
    public Page<Room> getRoomsByTerm(int pageNo, int pageSize, String term) {
        if (checkIfRoomType(term))
            return roomRepo.findByRoomType(RoomType.valueOf(term.toUpperCase()), PageRequest.of(pageNo, pageSize));
        else
            return roomRepo.findByTerm("%" + term + "%", PageRequest.of(pageNo, pageSize));
    }

    private static boolean checkIfRoomType(String str) {
        boolean isRoomType = false;
        for (RoomType r : RoomType.values()) {
            if (r.name().equalsIgnoreCase(str)) {
                isRoomType = true;
                break;
            }
        }
        return isRoomType;
    }

    @Override
    public String deleteRoom(Long id) {
        try {
            Room r = this.getRoomById(id);
            roomRepo.delete(r);
            ImageUtils.deleteImage(ImageUtils.roomImageDirectory, r.getImageUrl());
        } catch (DataIntegrityViolationException e) {
            return "Could not delete room with id " + id + ", room might be reserved";
        }
        return "Deleted Room with id: " + id;
    }

    @Override
    public ByteArrayResource getRoomImage(String roomName) throws IOException {
        File folder = new File(ImageUtils.roomImageDirectory);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                File f = listOfFiles[i];
                if (f.getName().trim().equalsIgnoreCase(roomName.trim())) {
                    return new ByteArrayResource(Files.readAllBytes(Paths.get(listOfFiles[i].getAbsolutePath())));
                }
            }
        }
        return null;
    }


}
