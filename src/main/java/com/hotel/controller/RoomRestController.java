package com.hotel.controller;

import com.hotel.entity.room.Room;
import com.hotel.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@Secured("ADMIN")
@RequestMapping("/api/rooms")
public class RoomRestController {
    RoomService roomService;
    public RoomRestController(RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Room>> getRooms(){
        return new ResponseEntity<>(roomService.findAllRooms(), HttpStatus.OK);
    }
}
