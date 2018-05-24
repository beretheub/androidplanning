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
import com.example.berenice.androidplanning.task.CostaffListAdapter;

import java.util.ArrayList;

/**
 * Activity to display the schedule of one member, during one day, with the different tasks and times
 */

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TODO needs to receive the task (or the id) and update multiple elements of the activity


        //TODO look for the colleagues in the database and populate the List
        //generate list
        ArrayList<String> list = new ArrayList<String>();
        list.add("Eco-respo");
        list.add("Signaleur S16");
        list.add("Service entrée");
        list.add("Rangement SAS");

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