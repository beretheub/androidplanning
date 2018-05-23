package com.example.berenice.androidplanning.database;

/**
 * Class to represent one line of the Task table
 */
public class Task {
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
}
