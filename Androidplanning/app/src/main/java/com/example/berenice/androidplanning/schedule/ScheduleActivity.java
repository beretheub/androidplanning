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

        int userID;
        String currentDay;

        //Ask for SMS permission
        if (!(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CALENDAR)
                == PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CALENDAR},
                    0);
        }

        //Ask for SMS permission
        if (!(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CALENDAR)
                == PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_CALENDAR},
                    0);
        }

            Calendar beginTime = Calendar.getInstance();
            Calendar endTime = Calendar.getInstance();
            Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                        endTime.getTimeInMillis())
                .putExtra(Events.TITLE, "Yoga")
                .putExtra(Events.DESCRIPTION, "Group class");
            startActivity(intent);

        /*
        Calendar cal = Calendar.getInstance();

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor/item/event");
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,cal.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        intent.putExtra(CalendarContract.Events.TITLE, "testEvent");
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "This is a sample description");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "My Guest House");
        startActivity(intent);*/

        //Access shared preferences
        SharedPreferences prefs = getSharedPreferences("PlanningPreferences", MODE_PRIVATE);
        userID = prefs.getInt("userID", 0);
        currentDay = prefs.getString("Day", "4");

        StaffDao dao = new StaffDao(this);
        dao.open();
        Staff currentStaff = dao.findStaffById(userID);
        dao.close();

        //Update fields
        TextView dayTextView = findViewById(R.id.day);
        dayTextView.setText("J" + currentDay);
        TextView nameStaff = findViewById(R.id.nameStaff);
        nameStaff.setText(currentStaff.getName().toUpperCase() + ", " + currentStaff.getFirstname());

        //generate list
        QueryHandler qh = new QueryHandler();
        final ArrayList<Task> list = qh.getTasksFromStaff(this, userID);

        //instantiate custom adapter
        TasksListAdapter adapter = new TasksListAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView) findViewById(R.id.listTasks);
        lView.setAdapter(adapter);

        //details about the day
       ImageButton day_details = (ImageButton) findViewById(R.id.day_details);
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
                intent.putExtra("currentTask", String.valueOf(list.get(position).getId()));
                startActivity(intent);
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


