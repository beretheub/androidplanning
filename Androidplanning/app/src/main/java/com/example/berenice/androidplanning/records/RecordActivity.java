package com.example.berenice.androidplanning.records;

import android.content.Context;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.berenice.androidplanning.R;
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

        //TODO needs to receive the task (or the id) and update multiple elements of the activity


        //generate list
        ArrayList<Task> list = new ArrayList<Task>();
        Task task1 = new Task(0, "Eco-Respo", "7:00", "7:15", "9:15", "Vérifier tri, peser, ramassr déchets");
        list.add(task1);

        Task task2 = new Task(0, "S17", "9:15", "10:00", "15:00", "Signaler puis débaliser");
        list.add(task2);


        //instantiate custom adapter
        TasksListAdapter adapter = new TasksListAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.listTasks);
        lView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        menu.getItem(1).setVisible(false);
        return true;
    }
}