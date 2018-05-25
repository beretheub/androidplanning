package com.example.berenice.androidplanning.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class to generate the needed database
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String CREATE_TABLE_STAFF = Constants.CREATE_TABLE_STAFF;

    private static final String CREATE_TABLE_TASKS = Constants.CREATE_TABLE_TASKS;

    private static final String CREATE_TABLE_TASKS_STAFF = Constants.CREATE_TABLE_TASKS_STAFF;

    private static final String CREATE_TABLE_CARS = Constants.CREATE_TABLE_CARS;

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STAFF);
        db.execSQL(CREATE_TABLE_TASKS);
        db.execSQL(CREATE_TABLE_TASKS_STAFF);
        db.execSQL(CREATE_TABLE_CARS);
    }

    public static final String DROP_TABLE_CARS = Constants.DROP_TABLE_CARS;
    public static final String DROP_TABLE_STAFF = Constants.DROP_TABLE_STAFF;
    public static final String DROP_TABLE_TASKS = Constants.DROP_TABLE_TASKS;
    public static final String DROP_TABLE_TASKS_STAFF = Constants.DROP_TABLE_TASKS_STAFF;

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_CARS);
        db.execSQL(DROP_TABLE_STAFF);
        db.execSQL(DROP_TABLE_TASKS_STAFF);
        db.execSQL(DROP_TABLE_TASKS);
        onCreate(db);
    }

    public void dropDatabase(SQLiteDatabase db) {
        db.execSQL(DROP_TABLE_CARS);
        db.execSQL(DROP_TABLE_STAFF);
        db.execSQL(DROP_TABLE_TASKS_STAFF);
        db.execSQL(DROP_TABLE_TASKS);
    }


}

