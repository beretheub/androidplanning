package com.example.berenice.androidplanning.records;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.berenice.androidplanning.R;
import com.example.berenice.androidplanning.database.QueryHandler;
import com.example.berenice.androidplanning.database.Staff;
import com.example.berenice.androidplanning.database.StaffDao;
import com.example.berenice.androidplanning.records.TasksListAdapter;
import com.example.berenice.androidplanning.database.Task;
import java.util.ArrayList;

/**
 * Activity to display the schedule of one member, during one day, with the different tasks and times
 */

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int userID = 1;
        String currentDay = "";

        //Access shared preferences
        SharedPreferences prefs = getSharedPreferences("PlanningPreferences", MODE_PRIVATE);
        userID = prefs.getInt("userID", 0);
        currentDay = prefs.getString("Day", "1");

        StaffDao dao = new StaffDao(this);
        dao.open();
        Staff currentStaff = dao.findStaffById(userID);
        dao.close();

        //Update fields
        TextView dayTextView = findViewById(R.id.day);
        dayTextView.setText("J" + currentDay);
        TextView nameStaff = findViewById(R.id.nameStaff);
        nameStaff.setText(currentStaff.getName().toUpperCase() + ", "+ currentStaff.getFirstname());

        //generate list
        QueryHandler qh = new QueryHandler();
        ArrayList<Task> list = qh.getTasksFromStaff(this, userID);

        //instantiate custom adapter
        TasksListAdapter adapter = new TasksListAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView) findViewById(R.id.listTasks);
        lView.setAdapter(adapter);

        TextView day = (TextView) findViewById(R.id.day);
        day.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                // Réagir au clic

            }

        });
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
            menu.getItem(1).setVisible(false);
            return true;
        }
    }

