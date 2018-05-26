package com.example.berenice.androidplanning.database;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

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

    /**
     * Gets all the staff of a certain task
     * @param context
     * @param idTask
     * @return
     */
    public ArrayList<Staff> getCostaff(Context context, int idTask)
    {
        StaffDao sdao = new StaffDao(context);
        TaskStaffDao tsdao = new TaskStaffDao(context);

        sdao.open();
        tsdao.open();

        ArrayList<Integer> staffIDs = tsdao.getStaffOfTask(idTask);
        ArrayList<Staff> result = new ArrayList<>();

        for (int id : staffIDs){
            result.add(sdao.findStaffById(id));
        }

        sdao.close();
        tsdao.close();

        return result;
    }

    /**
     * Finds the car and driver of a task-staff affection
     * @param context
     * @param taskID
     * @param userID
     * @return
     */
    public ArrayList<Object> getCarDriver(Context context, int taskID, int userID)
    {
        TaskStaffDao tsdao = new TaskStaffDao(context);
        StaffDao sdao = new StaffDao(context);
        CarDao cdao = new CarDao(context);
        tsdao.open();

        int carID = tsdao.getCarOfTaskStaff(taskID, userID);
        if (carID == 0)
            return null;

        int driverID = tsdao.getDriverOfCar(carID);

        tsdao.close();

        cdao.open();
        sdao.open();
        Staff driver = sdao.findStaffById(driverID);
        Car car = cdao.findCar(carID);
        cdao.close();
        sdao.close();

        ArrayList<Object> result = new ArrayList<>();
        result.add(car);
        result.add(driver);

        return result;
    }

    public ArrayList<Staff> getPassengersCar(Context context, int carID)
    {
        TaskStaffDao tsdao = new TaskStaffDao(context);
        StaffDao sdao = new StaffDao(context);

        tsdao.open();
        ArrayList<Integer> passengersID = tsdao.getPassengersOfCar(carID);
        tsdao.close();

        ArrayList<Staff> result = new ArrayList<>();
        sdao.open();
        for (int i:passengersID){
            result.add(sdao.findStaffById(i));
        }
        sdao.close();

        return result;
    }

    public ArrayList<Staff> getStaffOnRace(Context context)
    {
        ArrayList<Staff> res = new ArrayList<>();

        TaskStaffDao tsdao = new TaskStaffDao(context);
        StaffDao sdao = new StaffDao(context);

        tsdao.open();
        ArrayList<Integer> staffIds = tsdao.getStaffOnRace();
        tsdao.close();

        sdao.open();
        for (int i:staffIds){
            res.add(sdao.findStaffById(i));
        }
        sdao.close();
        Collections.sort(res);

        return res;
    }
}
