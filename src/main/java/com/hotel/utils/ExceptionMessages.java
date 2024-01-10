package com.hotel.utils;

public class ExceptionMessages {
    public static String EntityNotFoundMessage(String entityName, Number id){
        return "Could not find " + entityName + " with id: " + id;
    }

}
