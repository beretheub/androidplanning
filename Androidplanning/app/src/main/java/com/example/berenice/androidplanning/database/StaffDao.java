package com.example.berenice.androidplanning.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Class to dialogue with the Staff table
 */
public class StaffDao extends DaoBase {
    public static final String TABLE_CREATE = Constants.CREATE_TABLE_STAFF;

    public static final String TABLE_DROP = Constants.DROP_TABLE_STAFF;

    public StaffDao(Context pContext) {
        super(pContext);
    }

    /**
     * @param s the new staff to add
     */
    public void addStaff(Staff s) {
        ContentValues value = new ContentValues();
        value.put(Constants.STAFF_NAME, s.getName());
        value.put(Constants.STAFF_FIRSTNAME, s.getFirstname());
        value.put(Constants.STAFF_PHONE, s.getPhonenumber());
        value.put(Constants.STAFF_ROLE, s.getRole());
        value.put(Constants.STAFF_SECTOR, s.getSector());
        value.put(Constants.STAFF_TALKIEL, s.getTalkieLong());
        value.put(Constants.STAFF_TALKIEC, s.getTalkieCourt());

        mDb.insert(Constants.TABLE_STAFF, null, value);
    }

    /**
     * @param id id of the wanted staff
     */
    public Staff findStaff(long id) {
        Cursor c = getDb().rawQuery("select "
                + Constants.STAFF_ID  + " , "
                + Constants.STAFF_NAME  + " , "
                + Constants.STAFF_FIRSTNAME  + " , "
                + Constants.STAFF_PHONE  + " , "
                + Constants.STAFF_ROLE  + " , "
                + Constants.STAFF_SECTOR + " , "
                + Constants.STAFF_TALKIEL + " , "
                + Constants.STAFF_TALKIEC
                + " from " + Constants.TABLE_STAFF
                + " where " + Constants.STAFF_ID + " = ?", new String[]{String.valueOf(id)});

        if (c.moveToFirst()== false) {return null;}

        boolean talkiel, talkiec;

        if (c.getInt(6) == 0) {talkiel = false;}
        else {talkiel = true;}
        if (c.getInt(7) == 0) {talkiec = false;}
        else {talkiec = true;}


        return new Staff(c.getInt(0),
                c.getString(1),
                c.getString(2),
                c.getString(3),
                c.getString(4),
                c.getString(5),
                talkiel, talkiec);
    }
}
