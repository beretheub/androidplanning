package com.example.berenice.androidplanning.schedule;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;


import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import android.widget.TextView;
import android.widget.Toast;

import com.example.berenice.androidplanning.R;
import com.example.berenice.androidplanning.database.QueryHandler;
import com.example.berenice.androidplanning.database.Staff;
import com.example.berenice.androidplanning.database.StaffDao;

import com.example.berenice.androidplanning.database.Task;
import com.example.berenice.androidplanning.task.taskActivity;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Activity to display the schedule of one member, during one day, with the different tasks and times
 */

public class ScheduleActivity extends AppCompatActivity {
    Button Close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        //Access shared preferences
        SharedPreferences prefs = getSharedPreferences("PlanningPreferences", MODE_PRIVATE);
        int userID = prefs.getInt("userID", 0);
        String currentDay = prefs.getString("Day", "1");

        toolbar.setTitle(R.string.title_activity_fiche+ " J" + currentDay );
        setSupportActionBar(toolbar);

        //find current Staff if there is one
        StaffDao dao = new StaffDao(this);
        dao.open();
        Staff currentStaff = dao.findStaffById(userID);
        dao.close();

        //Update fields
        TextView nameStaff = findViewById(R.id.nameStaff);
        nameStaff.setText(currentStaff.getName().toUpperCase() + ", " + currentStaff.getFirstname());

        //generate taskList
        QueryHandler qh = new QueryHandler();
        final ArrayList<Task> taskList = qh.getTasksFromStaff(this, userID);

        //instantiate custom adapter
        TasksListAdapter adapter = new TasksListAdapter(taskList, this);

        //handle listview and assign adapter
        ListView lView = (ListView) findViewById(R.id.listTasks);
        lView.setAdapter(adapter);

        //details about the day
       Button day_details = (Button) findViewById(R.id.day_details);
        day_details.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                showPopup();
            }
            });

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ScheduleActivity.this, taskActivity.class);
                intent.putExtra("currentTask", String.valueOf(taskList.get(position).getId()));
                startActivity(intent);
            }
        });

        Button export_calendar = (Button) findViewById(R.id.export_calendar);
        export_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean succes = false;
                for (Task t:taskList) {
                    if(!t.exportToCalender(getBaseContext())){
                        Toast.makeText(ScheduleActivity.this, "Export failed!",
                                Toast.LENGTH_SHORT).show();
                        return;}

                }
                Toast.makeText(ScheduleActivity.this, "Export successful",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private PopupWindow pw;

    private void showPopup() {
        try {
// We need to get the instance of the LayoutInflater
            Context mContext = this.getBaseContext();
            LayoutInflater inflater = (LayoutInflater)
                    mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_day,
                    (ViewGroup) findViewById(R.id.popup_day));

            //Set content
            SharedPreferences prefs =
                    getSharedPreferences("PlanningPreferences", MODE_PRIVATE);
            ImageView profileView = (ImageView)layout.findViewById(R.id.profil);
            String currentDay= prefs.getString("Day","1");
            switch (currentDay) {
                case "1":
                    profileView.setImageResource(R.drawable.profil_1);
                    break;
                case "2":
                    profileView.setImageResource(R.drawable.profil_2);
                    break;
                case "3":
                    profileView.setImageResource(R.drawable.profil_3);
                    break;
                case "4":
                    profileView.setImageResource(R.drawable.profil_4);
                    break;
                case "5":
                    profileView.setImageResource(R.drawable.profil_5);
                    break;
            }

            //Read information of the day
            InputStreamReader is = new InputStreamReader(getBaseContext().getAssets()
                    .open("planningDB/J" + currentDay + "/infos.txt"), StandardCharsets.ISO_8859_1);

            BufferedReader reader = new BufferedReader(is);
            int i = 0;
            String line;
            String text[] = new String[6];
            while ((line = reader.readLine())!= null){
                text[i] = line;
                i++;
            }

            reader.close();
            is.close();

            TextView currentText = (TextView) layout.findViewById(R.id.popup_line1);
            currentText.setText(text[0]);
            currentText = (TextView) layout.findViewById(R.id.popup_line2);
            currentText.setText(text[1]);
            currentText = (TextView) layout.findViewById(R.id.popup_line3);
            currentText.setText(text[2]);
            currentText = (TextView) layout.findViewById(R.id.popup_line4);
            currentText.setText(text[3]);
            currentText = (TextView) layout.findViewById(R.id.popup_line5);
            currentText.setText(text[4]);
            currentText = (TextView) layout.findViewById(R.id.popup_line6);
            currentText.setText(text[5]);

            pw = new PopupWindow(layout, 900, 1270, true);
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);



            Close = (Button) layout.findViewById(R.id.close_popup);
            Close.setOnClickListener(cancel_button);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button = new View.OnClickListener() {
        public void onClick(View v) {
            pw.dismiss();
        }
    };
}


