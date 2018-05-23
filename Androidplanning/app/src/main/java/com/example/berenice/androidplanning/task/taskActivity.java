package com.example.berenice.androidplanning.task;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.berenice.androidplanning.R;

import java.util.ArrayList;

public class taskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //generate list
        ArrayList<String> list = new ArrayList<String>();
        list.add("Till");
        list.add("Béré");

        //instantiate custom adapter
        CotaffListAdapter adapter = new CotaffListAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.listCostaff);
        lView.setAdapter(adapter);

    }

}
