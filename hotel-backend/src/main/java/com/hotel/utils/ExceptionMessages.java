package com.hotel.utils;

public class ExceptionMessages {
    public static String EntityNotFound(String entityName, Number id){
        return "Could not find " + entityName + " with id: " + id;
    }
    public static String EntityNotFound(String entityName, String name){
        return "Could not find " + entityName + ": " + name;
    }
    public static String AlreadyExists(String entityName, String name){
        return entityName + ": " + name + " already exists";
    }
}
