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
import android.widget.ListView;
import android.widget.PopupWindow;

import android.widget.TextView;

import com.example.berenice.androidplanning.R;
import com.example.berenice.androidplanning.database.QueryHandler;
import com.example.berenice.androidplanning.database.Staff;
import com.example.berenice.androidplanning.database.StaffDao;

import com.example.berenice.androidplanning.database.Task;
import com.example.berenice.androidplanning.task.taskActivity;

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
        setSupportActionBar(toolbar);

        //Access shared preferences
        SharedPreferences prefs = getSharedPreferences("PlanningPreferences", MODE_PRIVATE);
        int userID = prefs.getInt("userID", 0);
        String currentDay = prefs.getString("Day", "1");

        toolbar.setTitle(R.id.nameTask_schedule+ " J" + currentDay );

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
                //Ask for calendar permission
                if (!(ContextCompat.checkSelfPermission(getBaseContext(),
                        Manifest.permission.READ_CALENDAR)
                        == PackageManager.PERMISSION_GRANTED)) {
                    ActivityCompat.requestPermissions(ScheduleActivity.this,
                            new String[]{Manifest.permission.READ_CALENDAR},
                            0);
                }
                //Ask for calendar permission
                if (!(ContextCompat.checkSelfPermission(getBaseContext(),
                        Manifest.permission.WRITE_CALENDAR)
                        == PackageManager.PERMISSION_GRANTED)) {
                    ActivityCompat.requestPermissions(ScheduleActivity.this,
                            new String[]{Manifest.permission.WRITE_CALENDAR},
                            0);
                }

                for (Task t:taskList) {
                    t.exportToCalender(getBaseContext());
                }
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


