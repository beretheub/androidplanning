package com.example.berenice.androidplanning.database;

public class TaskStaff {
    int id;
    String task;
    String staff;
    Boolean respo;
    Boolean sheet;
    String car;
    String driver;
    Boolean onTheRace;

    public TaskStaff(int id, String task, String staff, Boolean respo, Boolean sheet, String car, String driver, Boolean onTheRace) {
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

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
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

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Boolean getOnTheRace() {
        return onTheRace;
    }

    public void setOnTheRace(Boolean onTheRace) {
        this.onTheRace = onTheRace;
    }
}
