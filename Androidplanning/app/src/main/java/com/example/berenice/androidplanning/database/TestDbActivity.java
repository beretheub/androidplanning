package com.example.berenice.androidplanning.database;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.berenice.androidplanning.R;

public class TestDbActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Context context = this;

        Button dbImport = (Button) findViewById(R.id.dbImport);
        dbImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImportHandler ih = new ImportHandler(context);
                ih.importAll("4");
            }
        });

        Button testStaff = (Button) findViewById(R.id.dbDrop);
        testStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDatabase(Constants.DATABASE_NAME);
            }
        });
    }

}
