package com.hotel.entity.room;

import java.util.ArrayList;
import java.util.List;

public enum RoomType {
    SINGLE, DOUBLE, TRIPLE, STUDIO, SUITE, DELUXE;

    public static List<String> RoomTypeList(){
        List<String> roomTypes = new ArrayList<>();
        for (RoomType roomType : RoomType.values()) {
            roomTypes.add(roomType.name());
        }
        return roomTypes;
    }

}
