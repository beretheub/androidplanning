package com.example.berenice.androidplanning.task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;

import com.example.berenice.androidplanning.R;

import java.util.ArrayList;

/**
 * Activity to display one task with all the information and colleagues
 */
public class taskActivity extends AppCompatActivity {

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
        list.add("Till");
        list.add("Béré");
        list.add("Bab");

        //instantiate custom adapter
        CostaffListAdapter adapter = new CostaffListAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.listCostaff);
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
