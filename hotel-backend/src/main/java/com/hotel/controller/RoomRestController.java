package com.hotel.controller;

import com.hotel.dto.RoomDTO;
import com.hotel.entity.room.Room;
import com.hotel.entity.room.RoomType;
import com.hotel.service.RoomService;
import com.hotel.utils.ImageUtils;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
//@Secured("ADMIN")
@RequestMapping("/api/rooms")
public class RoomRestController {
    RoomService roomService;
    private static final Logger LOG = LoggerFactory.getLogger(RoomRestController.class);

    public RoomRestController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/{roomId:\\d+}")
    public ResponseEntity<Room> getRoom(@PathVariable Long roomId) {
        return new ResponseEntity<>(roomService.getRoomById(roomId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Room>> getRooms(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                               @RequestParam(value = "pageSize", defaultValue = "25") int pageSize) {
        return new ResponseEntity<>(roomService.getAllRooms(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Room>> getRoomByTerm(@RequestParam("term") String term,
                                                    @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                    @RequestParam(value = "pageSize", defaultValue = "25") int pageSize) {
        return new ResponseEntity<>(roomService.getRoomsByTerm(pageNo, pageSize, term), HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<Room> createRoom(@Valid @RequestBody Room room){
        return new ResponseEntity<>(roomService.saveRoom(room), HttpStatus.CREATED);
    }

    @PutMapping("/{roomId:\\d+}")
    public ResponseEntity<Room> updateRoom(@Valid @RequestBody Room updatedRoom, @PathVariable Long roomId) {
        return new ResponseEntity<>(roomService.updateRoom(roomId, updatedRoom), HttpStatus.OK);
    }

    @DeleteMapping("/{roomId:\\d+}")
    public ResponseEntity<String> deleteRoom(@PathVariable String roomId) {
        return new ResponseEntity<>(roomService.deleteRoom(Long.parseLong(roomId)), HttpStatus.OK);
    }
    @GetMapping("/room-types")
    public ResponseEntity<List<String>> getRoomTypes() {
        List<String> roomTypes = new ArrayList<>();
        for (RoomType roomType : RoomType.values())
            roomTypes.add(roomType.name());
        return new ResponseEntity<>(roomTypes, HttpStatus.OK);
    }
    @GetMapping(value = "/image/{roomName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody Resource getImage(@PathVariable String roomName) throws IOException {
        return roomService.getRoomImage(roomName);
    }
}
