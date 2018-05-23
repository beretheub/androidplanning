package com.example.berenice.androidplanning.database;

/**
 * Class to represent one line of the Car table
 */
public class Car {
    int id;
    String name;
    String id_String;

    public Car(int id, String name, String id_String) {
        super();
        this.id = id;
        this.name = name;
        this.id_String = id_String;
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

    public String getId_String() {
        return id_String;
    }

    public void setId_String(String id_String) {
        this.id_String = id_String;
    }
}
