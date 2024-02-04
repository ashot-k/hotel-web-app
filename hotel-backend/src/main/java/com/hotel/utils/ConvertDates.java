package com.hotel.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ConvertDates {

    public static LocalDate YMDtoDMY(String date){
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDateString = outputFormat.format(inputFormat.parse(date));
        return LocalDate.parse(formattedDateString, outputFormat);
    }
}
