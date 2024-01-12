package com.hotel.utils;

public class ExceptionMessages {
    public static String EntityNotFoundMessage(String entityName, Number id){
        return "Could not find " + entityName + " with id: " + id;
    }
    public static String EntityNotFoundMessage(String entityName, String name){
        return "Could not find " + entityName + ": " + name;
    }
}
