package com.example.berenice.androidplanning.database;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.example.berenice.androidplanning.DateFormater;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Class to represent one line of the Task table
 */
public class Task implements Comparable{
    int id;
    String name;
    String departure;
    String begin;
    String end;
    String Description;

    public Task(int id, String name, String departure, String begin, String end, String description) {
        super();
        this.id = id;
        this.name = name;
        this.departure = departure;
        this.begin = begin;
        this.end = end;
        Description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void exportToCalender(Context context) {
        Calendar departureTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();

        //Set departure Time
        String departureRaw = DateFormater.formatDateToTime(getDeparture());
        int departureHour = Integer.parseInt(departureRaw.substring(0, 2));
        int departureMinute = Integer.parseInt(departureRaw.substring(3, 5));
        departureTime.set(Calendar.HOUR_OF_DAY, departureHour);
        departureTime.set(Calendar.MINUTE, departureMinute);

        //Set begin Time
        String endRaw = DateFormater.formatDateToTime(getEnd());
        int endHour = Integer.parseInt(endRaw.substring(0, 2));
        int endMinute = Integer.parseInt(endRaw.substring(3, 5));
        endTime.set(Calendar.HOUR_OF_DAY, endHour);
        endTime.set(Calendar.MINUTE, endMinute);


        ContentResolver cr = context.getContentResolver();
        ContentValues values = new ContentValues();

        values.put(CalendarContract.Events.DTSTART, departureTime.getTimeInMillis());
        values.put(CalendarContract.Events.DTEND, endTime.getTimeInMillis());
        values.put(CalendarContract.Events.TITLE, getName());
        values.put(CalendarContract.Events.DESCRIPTION, getDescription());

        TimeZone timeZone = TimeZone.getDefault();
        values.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone.getID());

        // Default calendar
        values.put(CalendarContract.Events.CALENDAR_ID, 1);

        values.put(CalendarContract.Events.HAS_ALARM, 1);


        // Insert event to calendar
        if (ActivityCompat.checkSelfPermission
                (context, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED)
            {return;}

        Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return -1 * ((Task) o).getDeparture().compareTo(this.getDeparture());
    }
}
