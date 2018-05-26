package com.example.berenice.androidplanning.database;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Handler class to imput the different tables
 */
public class ImportHandler {

    Context context;

    public ImportHandler(Context context) {
        this.context = context;
    }

    /**
     * Imports all the tables from the fixed directory (assets)
     * @param day
     */
    public void importAll(String day)
    {
        context.deleteDatabase(Constants.DATABASE_NAME);
        importTaskStaff(context, day);
        importCars(context, day);
        importTasks(context, day);
        importStaff(context, day);
    }

    public void importStaff(Context context, String day)
    {
        try {
            InputStreamReader is = new InputStreamReader(context.getAssets()
                    .open("planningDB/J" + day + "/T_Staff.txt"), StandardCharsets.ISO_8859_1);

            BufferedReader reader = new BufferedReader(is);
            String line;
            StaffDao dao = new StaffDao(context);
            dao.open();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");
                boolean talkiel, talkiec;

                if (values[9].equalsIgnoreCase("0")) {talkiel = false;}
                else {talkiel = true;}
                if (values[10].equalsIgnoreCase("0")) {talkiec = false;}
                else {talkiec = true;}

                Staff newEntry = new Staff(Integer.parseInt(values[0]),
                        values[1],
                        values[2],
                        values[6],
                        values[7],
                        values[8],
                        talkiel, talkiec);

                dao.addStaff(newEntry);
            }
            reader.close();
            is.close();
            dao.close();
        }
        catch (IOException e) {e.printStackTrace();}

    }

    public void importTasks(Context context, String day)
    {
        try {
            InputStreamReader is = new InputStreamReader(context.getAssets()
                    .open("planningDB/J" + day + "/T_Taches.txt"), StandardCharsets.ISO_8859_1);

            BufferedReader reader = new BufferedReader(is);
            String line;
            TaskDao dao = new TaskDao(context);
            dao.open();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");

                Task newEntry = new Task(Integer.parseInt(values[0]),
                        values[1],
                        values[2],
                        values[3],
                        values[4],
                        values[5]);

                dao.addTask(newEntry);
            }
            reader.close();
            is.close();
            dao.close();
        }
        catch (IOException e) {e.printStackTrace();}

    }

    public void importCars(Context context, String day)
    {
        try {
            InputStreamReader is = new InputStreamReader(context.getAssets()
                    .open("planningDB/J" + day + "/T_Voitures.txt"), StandardCharsets.ISO_8859_1);

            BufferedReader reader = new BufferedReader(is);
            String line;
            CarDao dao = new CarDao(context);
            dao.open();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");

                Car newEntry = new Car(Integer.parseInt(values[0]),
                        values[2],
                        values[1]);

                dao.addCar(newEntry);
            }
            reader.close();
            is.close();
            dao.close();
        }
        catch (IOException e) {e.printStackTrace();}

    }

    public void importTaskStaff(Context context, String day)
    {
        try {
            InputStreamReader is = new InputStreamReader(
                    context.getAssets().open(
                            "planningDB/J" + day + "/T_TachesEtStaffeurs.txt"),
                    StandardCharsets.ISO_8859_1);

            BufferedReader reader = new BufferedReader(is);
            String line;
            TaskStaffDao dao = new TaskStaffDao(context);
            dao.open();
            boolean sheet, respo, onTheRace;
            int taskID, staffID, carID, driverID;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");

                if (values[3].equalsIgnoreCase("0")) {respo = false;}
                else {respo = true;}
                if (values[4].equalsIgnoreCase("0")) {sheet = false;}
                else {sheet = true;}
                if (values[7].equalsIgnoreCase("0")) {onTheRace = false;}
                else {onTheRace = true;}

                if (values[1].equalsIgnoreCase("")) {continue;}
                else {taskID = Integer.parseInt(values[1]);}
                if (values[2].equalsIgnoreCase("")) {continue;}
                else {staffID = Integer.parseInt(values[2]);}
                if (values[5].equalsIgnoreCase("")) {carID = 0;}
                else {carID = Integer.parseInt(values[5]);}
                if (values[6].equalsIgnoreCase("")) {driverID = 0;}
                else {driverID = Integer.parseInt(values[6]);}

                TaskStaff newEntry = new TaskStaff(Integer.parseInt(values[0]),
                        taskID,
                        staffID,
                        respo,
                        sheet,
                        carID,
                        driverID,
                        onTheRace);

                dao.addTaskStaff(newEntry);
            }
            reader.close();
            is.close();
            dao.close();
        }
        catch (IOException e) {e.printStackTrace();}

    }
}
