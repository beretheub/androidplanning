package com.example.berenice.androidplanning.database;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ImportHandler {
    public static void importSaff(Context context, int day)
    {
        try {
            InputStreamReader is = new InputStreamReader(context.getAssets()
                    .open("planningDB/J" + String.valueOf(day) + "/T_Staff.txt"));

            BufferedReader reader = new BufferedReader(is);
            reader.readLine();
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

                Staff newEntry = new Staff(1,
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
}
