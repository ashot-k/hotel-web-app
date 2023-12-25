package com.hotel.controller;

import com.hotel.entity.room.Room;
import com.hotel.service.RoomService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Secured("ADMIN")
@RequestMapping("/api/rooms")
public class RoomRestController {
    RoomService roomService;
    private static final Logger LOG = LoggerFactory.getLogger(RoomRestController.class);

    public RoomRestController(RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRooms(@PathVariable Long roomId) {
        return new ResponseEntity<>(roomService.getRoomById(roomId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Room>> getRooms(){
        return new ResponseEntity<>(roomService.findAllRooms(), HttpStatus.OK);
    }
    @PostMapping("/create-room")
    public ResponseEntity<Room> createRoom(@Valid @RequestBody Room room) {
        return new ResponseEntity<>(roomService.saveRoom(room), HttpStatus.CREATED);
    }

    @PutMapping("/update-room/{id}")
    public ResponseEntity<Room> updateRoom(@Valid @RequestBody Room updatedRoom, @PathVariable Long id) {
        return new ResponseEntity<>(roomService.updateRoom(id, updatedRoom), HttpStatus.OK);
    }

    @DeleteMapping("/delete-user/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable String roomId) {
        return new ResponseEntity<>(roomService.deleteRoom(Long.parseLong(roomId)), HttpStatus.OK);
    }
}
