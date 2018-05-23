package com.example.berenice.androidplanning.database;

public class Staff {
    int id;
    String name;
    String firstname;
    String phonenumber;
    String role;
    String sector;
    Boolean talkieLong;
    Boolean talkieCourt;

    public Staff(int id, String name, String firstname, String phonenumber, String role,
                 String sector, Boolean talkieLong, Boolean talkieCourt) {
        super();
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.phonenumber = phonenumber;
        this.role = role;
        this.sector = sector;
        this.talkieLong = talkieLong;
        this.talkieCourt = talkieCourt;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Boolean getTalkieLong() {
        return talkieLong;
    }

    public void setTalkieLong(Boolean talkieLong) {
        this.talkieLong = talkieLong;
    }

    public Boolean getTalkieCourt() {
        return talkieCourt;
    }

    public void setTalkieCourt(Boolean talkieCourt) {
        this.talkieCourt = talkieCourt;
    }
}
