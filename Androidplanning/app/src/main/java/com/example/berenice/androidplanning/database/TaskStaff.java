package com.example.berenice.androidplanning.database;

/**
 * Class to represent one line of the TaskStaff table
 */
public class TaskStaff {
    int id;
    int task;
    int staff;
    Boolean respo;
    Boolean sheet;
    int car;
    int driver;
    Boolean onTheRace;

    public TaskStaff(int id, int task, int staff, Boolean respo, Boolean sheet, int car, int driver, Boolean onTheRace) {
        super();
        this.id = id;
        this.task = task;
        this.staff = staff;
        this.respo = respo;
        this.sheet = sheet;
        this.car = car;
        this.driver = driver;
        this.onTheRace = onTheRace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }

    public int getStaff() {
        return staff;
    }

    public void setStaff(int staff) {
        this.staff = staff;
    }

    public Boolean getRespo() {
        return respo;
    }

    public void setRespo(Boolean respo) {
        this.respo = respo;
    }

    public Boolean getSheet() {
        return sheet;
    }

    public void setSheet(Boolean sheet) {
        this.sheet = sheet;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    public int getDriver() {
        return driver;
    }

    public void setDriver(int driver) {
        this.driver = driver;
    }

    public Boolean getOnTheRace() {
        return onTheRace;
    }

    public void setOnTheRace(Boolean onTheRace) {
        this.onTheRace = onTheRace;
    }
}
