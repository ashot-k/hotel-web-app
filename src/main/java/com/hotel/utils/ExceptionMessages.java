package com.hotel.utils;

public class ExceptionMessages {
    public static String EntityNotFoundMessage(String entityName, String field,  Number value){
        return "Could not find " + entityName + " with "+ field + ": " + value;
    }
}
