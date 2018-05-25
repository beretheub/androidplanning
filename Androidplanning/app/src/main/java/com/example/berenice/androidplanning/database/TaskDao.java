package com.example.berenice.androidplanning.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Class to dialogue with the Task table
 */
public class TaskDao extends DaoBase {
    public static final String TABLE_CREATE = Constants.CREATE_TABLE_TASKS;

    public static final String TABLE_DROP = Constants.DROP_TABLE_TASKS;

    public TaskDao(Context pContext) {
        super(pContext);
    }

    /**
     * @param t the new task to add
     */
    public void addTask(Task t) {
        ContentValues value = new ContentValues();
        value.put(Constants.TASKS_ID, t.getId());
        value.put(Constants.TASKS_NAME, t.getName());
        value.put(Constants.TASKS_DEP, t.getDeparture());
        value.put(Constants.TASKS_BEG, t.getBegin());
        value.put(Constants.TASKS_END, t.getEnd());
        value.put(Constants.TASKS_DESCRIPTION, t.getDescription());

        mDb.insert(Constants.TABLE_TASKS, null, value);
    }

    /**
     * @param id id of the wanted staff
     */
    public Task findTask(long id) {
        Cursor c = getDb().rawQuery("select "
                + Constants.TASKS_ID  + " , "
                + Constants.TASKS_NAME + " , "
                + Constants.TASKS_DEP  + " , "
                + Constants.TASKS_BEG  + " , "
                + Constants.TASKS_END  + " , "
                + Constants.TASKS_DESCRIPTION
                + " from " + Constants.TABLE_TASKS
                + " where " + Constants.TASKS_ID + " = ?", new String[]{String.valueOf(id)});

        if (c.moveToFirst()== false) {return null;}

        return new Task(c.getInt(0),
                c.getString(1),
                c.getString(2),
                c.getString(3),
                c.getString(4),
                c.getString(5));
    }
}
