package com.example.berenice.androidplanning.database;

import android.content.Context;

import java.util.ArrayList;

/**
 * Class to handle all the complicated Database requests.
 */
public class QueryHandler {
    /**
     * Return all tasks of a staff identified by his id
     * @param context
     * @param idStaff
     * @return
     */
    public ArrayList<Task> getTasksFromStaff(Context context, int idStaff)
    {
        TaskStaffDao tsdao = new TaskStaffDao(context);
        TaskDao tdao = new TaskDao(context);

        tsdao.open();
        tdao.open();

        ArrayList<Integer> tasksIds = tsdao.getTasksOfStaff(idStaff);
        ArrayList<Task> result = new ArrayList<>();

        for (int id : tasksIds){
            result.add(tdao.findTask(id));
        }

        tsdao.close();
        tdao.close();

        return result;
    }
}