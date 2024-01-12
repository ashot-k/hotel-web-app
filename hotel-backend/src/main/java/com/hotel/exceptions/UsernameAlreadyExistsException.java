package com.hotel.exceptions;

public class UsernameAlreadyExistsException extends Exception{
    public UsernameAlreadyExistsException(String msg){
        super(msg);
    }
}
