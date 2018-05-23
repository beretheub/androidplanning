package com.example.berenice.androidplanning.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class CarDao extends DaoBase{
    public static final String TABLE_CREATE = Constants.CREATE_TABLE_CARS;

    public static final String TABLE_DROP = Constants.DROP_TABLE_CARS;

    public CarDao(Context pContext) {
        super(pContext);
    }

    /**
     * @param c the new car to add
     */
    public void addCar(Car c) {
        ContentValues value = new ContentValues();
        value.put(Constants.CARS_NAME, c.getName());
        value.put(Constants.CARS_NUMBER, c.getId_String());
        mDb.insert(Constants.TABLE_CARS, null, value);
    }

    /**
     * @param id id of the wanted car
     */
    public Car findCar(long id) {
        Cursor c = getDb().rawQuery("select "
                        + Constants.CARS_ID + ", "
                        + Constants.CARS_NAME + ", "
                        + Constants.CARS_NUMBER
                        + " from " + Constants.TABLE_CARS
                        + " where " + Constants.CARS_ID + " = ?", new String[]{String.valueOf(id)});

        if (c.moveToFirst()== false) {return null;}

        return new Car(c.getInt(0),
                c.getString(1),
                c.getString(2));
    }
}

