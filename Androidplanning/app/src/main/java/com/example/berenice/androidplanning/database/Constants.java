package com.example.berenice.androidplanning.database;

import android.provider.BaseColumns;

public final class Constants implements BaseColumns {
    //Database name
    public static final String DATABASE_NAME = "planning.db";

    //Database version
    public static final int DATABASE_VERSION = 1;

    // The table names
    public static final String TABLE_STAFF = "T_Staff";
    public static final String TABLE_TASKS = "T_Tâches";
    public static final String TABLE_TASKS_STAFF = "T_Tâches et staffeurs";
    public static final String TABLE_CARS = "T_Voitures";

    //Column names
    //Staff
    public static final String STAFF_ID = "_id";
    public static final String STAFF_NAME = "Nom";
    public static final String STAFF_FIRSTNAME = "Prénom";
    public static final String STAFF_PHONE = "Portable";
    public static final String STAFF_ROLE = "Rôle";
    public static final String STAFF_SECTOR = "Secteur";
    public static final String STAFF_TALKIEL = "Talkie long";
    public static final String STAFF_TALKIEC = "Talkie court";

    //Tasks
    public static final String TASKS_ID = "_id";
    public static final String TASKS_NAME = "Tâche";
    public static final String TASKS_DEP = "Départ";
    public static final String TASKS_BEG = "Début";
    public static final String TASKS_END = "Fin";
    public static final String TASKS_DESCRIPTION = "Description";

    //Tasks and staff
    public static final String TASKS_STAFF_ID = "_id";
    public static final String TASKS_STAFF_TASK = "Tâche";
    public static final String TASKS_STAFF_STAFF = "Staffeur";
    public static final String TASKS_STAFF_RESPO = "Respo";
    public static final String TASKS_STAFF_SHEET = "Fiche Respo";
    public static final String TASKS_STAFF_CAR = "Voiture";
    public static final String TASKS_STAFF_DRIVER = "Conducteur";
    public static final String TASKS_STAFF_RACE = "Signaleur";

    //Cars
    public static final String CARS_ID = "_id";
    public static final String CARS_NAME = "Nom";
    public static final String CARS_NUMBER = "N° Voiture";

    //TODO add column numbers ?

    public static final String CREATE_TABLE_STAFF = "create table "
            + Constants.TABLE_STAFF + "("
            + Constants.STAFF_ID  + " integer primary key autoincrement, "
            + Constants.STAFF_NAME  + " TEXT, "
            + Constants.STAFF_FIRSTNAME  + " TEXT, "
            + Constants.STAFF_PHONE  + " TEXT, "
            + Constants.STAFF_ROLE  + " TEXT, "
            + Constants.STAFF_SECTOR + " TEXT, "
            + Constants.STAFF_TALKIEL + " INTEGER, "
            + Constants.STAFF_TALKIEC  + " INTEGER);";

    public static final String CREATE_TABLE_TASKS = "create table "
            + Constants.TABLE_TASKS + "("
            + Constants.TASKS_ID  + " integer primary key autoincrement, "
            + Constants.TASKS_NAME + " TEXT, "
            + Constants.TASKS_DEP  + " TEXT, "
            + Constants.TASKS_BEG  + " TEXT, "
            + Constants.TASKS_END  + " TEXT, "
            + Constants.TASKS_DESCRIPTION  + " TEXT);";

    public static final String CREATE_TABLE_TASKS_STAFF = "create table "
            + Constants.TABLE_TASKS_STAFF + "("
            + Constants.TASKS_STAFF_ID  + " integer primary key autoincrement, "
            + Constants.TASKS_STAFF_TASK  + " TEXT, "
            + Constants.TASKS_STAFF_STAFF  + " TEXT, "
            + Constants.TASKS_STAFF_RESPO  + " INTEGER, "
            + Constants.TASKS_STAFF_SHEET  + " INTEGER, "
            + Constants.TASKS_STAFF_CAR  + " TEXT, "
            + Constants.TASKS_STAFF_DRIVER  + " TEXT, "
            + Constants.TASKS_STAFF_RACE  + " INTEGER);";

    public static final String CREATE_TABLE_CARS = "create table "
            + Constants.TABLE_CARS + "("
            + Constants.CARS_ID  + " integer primary key autoincrement, "
            + Constants.CARS_NAME  + " TEXT, "
            + Constants.CARS_NUMBER  + " TEXT);";

    public static final String DROP_TABLE_CARS = "DROP TABLE IF EXISTS " + Constants.TABLE_CARS + ";";
    public static final String DROP_TABLE_STAFF = "DROP TABLE IF EXISTS " + Constants.TABLE_STAFF + ";";
    public static final String DROP_TABLE_TASKS = "DROP TABLE IF EXISTS " + Constants.TABLE_TASKS + ";";
    public static final String DROP_TABLE_TASKS_STAFF = "DROP TABLE IF EXISTS " + Constants.TABLE_TASKS_STAFF + ";";

}
