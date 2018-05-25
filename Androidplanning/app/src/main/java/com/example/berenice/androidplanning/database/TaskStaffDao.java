package com.example.berenice.androidplanning.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.SyncStateContract;

import java.util.ArrayList;

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
        value.put(Constants.TASKS_STAFF_ID, t.getId());
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
                c.getInt(1),
                c.getInt(2),
                respo, sheet,
                c.getInt(5),
                c.getInt(6),
                race);
    }

    /**
     * Returns all the tasks (id) of a certain Staff
     * @param idStaff
     */
    public ArrayList<Integer> getTasksOfStaff(long idStaff) {
        String query;
        query = String.format("select %s.%s from %s inner join %s on %s.%s = %s.%s " +
                        "where %s.%s = ? order by %s.%s",
                Constants.TABLE_TASKS_STAFF,
                Constants.TASKS_STAFF_TASK,
                Constants.TABLE_TASKS_STAFF,
                Constants.TABLE_TASKS,
                Constants.TABLE_TASKS_STAFF,
                Constants.TASKS_STAFF_TASK,
                Constants.TABLE_TASKS,
                Constants.TASKS_ID,
                Constants.TABLE_TASKS_STAFF,
                Constants.TASKS_STAFF_STAFF,
                Constants.TABLE_TASKS,
                Constants.TASKS_BEG);

        Cursor c = getDb().rawQuery(query, new String[]{String.valueOf(idStaff)});
        ArrayList<Integer> result = new ArrayList<Integer>();

        while (c.moveToNext()) {

            result.add(c.getInt(0));
        }

        return result;
    }

    /**
     * Returns all the staff (id) of a certain task
     * @param idTask
     */
    public ArrayList<Integer> getStaffOfTask(long idTask) {
        String query;
        query = String.format("select %s from %s where %s = ? order by %s",
                Constants.TASKS_STAFF_STAFF,
                Constants.TABLE_TASKS_STAFF,
                Constants.TASKS_STAFF_TASK,
                Constants.TASKS_STAFF_STAFF);

        Cursor c = getDb().rawQuery(query, new String[]{String.valueOf(idTask)});
        ArrayList<Integer> result = new ArrayList<Integer>();

        while (c.moveToNext()) {

            result.add(c.getInt(0));
        }

        return result;
    }

    /**
     * Returns the car of a task-staff affection
     * @param idTask
     * @param idStaff
     */
    public int getCarOfTaskStaff(long idTask, long idStaff) {
        String query;
        query = String.format("select %s from %s where %s = ? AND %s = ?",
                Constants.TASKS_STAFF_CAR,
                Constants.TABLE_TASKS_STAFF,
                Constants.TASKS_STAFF_STAFF,
                Constants.TASKS_STAFF_TASK);

        Cursor c = getDb().rawQuery(
                query, new String[]{String.valueOf(idStaff),String.valueOf(idTask)});

        int result = 0;

        while (c.moveToNext()) {

            result = c.getInt(0);
        }

        return result;
    }

    /**
     * Returns the driver of a certain car
     * @param driverID
     */
    public int getDriverOfCar(long driverID) {
        String query;
        query = String.format("select %s from %s where %s = ?",
                Constants.TASKS_STAFF_DRIVER,
                Constants.TABLE_TASKS_STAFF,
                Constants.TASKS_STAFF_CAR);

        Cursor c = getDb().rawQuery(
                query, new String[]{String.valueOf(driverID)});

        int result = 0;

        while (c.moveToNext()) {

            result = c.getInt(0);
        }

        return result;
    }

    public ArrayList<Integer> getPassengersOfCar(long carID)
    {
        String query;
        query = String.format("select %s from %s where %s = ?",
                Constants.TASKS_STAFF_STAFF,
                Constants.TABLE_TASKS_STAFF,
                Constants.TASKS_STAFF_CAR);

        Cursor c = getDb().rawQuery(
                query, new String[]{String.valueOf(carID)});

        ArrayList<Integer> result = new ArrayList<>();

        while (c.moveToNext()) {

            result.add(c.getInt(0));
        }

        return result;
    }
}
