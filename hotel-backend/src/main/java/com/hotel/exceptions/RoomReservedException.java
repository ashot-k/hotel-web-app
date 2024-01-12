package com.hotel.exceptions;

public class RoomReservedException extends Exception {
    public RoomReservedException(String errorMessage) {
        super(errorMessage);
    }
}
