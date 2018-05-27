package com.example.berenice.androidplanning;

/*
 * Take a date as an argument, and return a simplified format with the time only
 */

public class DateFormater {
    public static String formatDateToTime(String date)
    {
        return date.substring(11,16);
    }
}
