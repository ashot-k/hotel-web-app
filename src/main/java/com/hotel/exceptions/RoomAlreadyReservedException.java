package com.hotel.exceptions;

public class RoomAlreadyReservedException extends Exception {
    public RoomAlreadyReservedException(String errorMessage) {
        super(errorMessage);
    }
}
