package com.example.berenice.androidplanning.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Class to dialogue with the TaskStaff table
 */
public class TaskStaffDao extends DaoBase {
    public static final String TABLE_CREATE = Constants.TABLE_TASKS_STAFF;

    public static final String TABLE_DROP = Constants.DROP_TABLE_TASKS_STAFF;

    public TaskStaffDao(Context pContext) {
        super(pContext);
    }

    /**
     * @param t the new task-staff to add
     */
    public void addTaskStaff(TaskStaff t) {
        ContentValues value = new ContentValues();
        value.put(Constants.TASKS_STAFF_TASK, t.getTask());
        value.put(Constants.TASKS_STAFF_STAFF, t.getStaff());
        value.put(Constants.TASKS_STAFF_RESPO, t.getRespo());
        value.put(Constants.TASKS_STAFF_SHEET, t.getSheet());
        value.put(Constants.TASKS_STAFF_CAR, t.getCar());
        value.put(Constants.TASKS_STAFF_DRIVER, t.getDriver());
        value.put(Constants.TASKS_STAFF_RACE, t.getOnTheRace());

        mDb.insert(Constants.TABLE_TASKS_STAFF, null, value);
    }

    /**
     * @param id id of the wanted task-staff
     */
    public TaskStaff findTask(long id) {
        String query;
        query = String.format("select %s , %s , %s , %s , %s , %s , %s , %s from %s where %s = ?",
                Constants.TASKS_STAFF_ID,
                Constants.TASKS_STAFF_TASK,
                Constants.TASKS_STAFF_STAFF,
                Constants.TASKS_STAFF_RESPO,
                Constants.TASKS_STAFF_SHEET,
                Constants.TASKS_STAFF_CAR,
                Constants.TASKS_STAFF_DRIVER,
                Constants.TASKS_STAFF_RACE,
                Constants.TABLE_TASKS_STAFF,
                Constants.TASKS_STAFF_ID);

        Cursor c = getDb().rawQuery(query, new String[]{String.valueOf(id)});

        if (c.moveToFirst()== false) {return null;}

        boolean respo, sheet, race;

        if (c.getInt(3) == 0) {respo = false;}
        else {respo = true;}
        if (c.getInt(4) == 0) {sheet = false;}
        else {sheet = true;}
        if (c.getInt(7) == 0) {race = false;}
        else {race = true;}

        return new TaskStaff(c.getInt(0),
                c.getString(1),
                c.getString(2),
                respo, sheet,
                c.getString(5),
                c.getString(6),
                race);
    }
}
