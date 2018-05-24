package com.example.berenice.androidplanning.database;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.berenice.androidplanning.R;

public class TestDbActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Context context = this;

        Button testStaff = (Button) findViewById(R.id.testStaffImport);
        testStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImportHandler.importSaff(context,4);
            }
        });

        final Button testStaffLoad = (Button) findViewById(R.id.testStaffDB);
        testStaffLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaffDao dao = new StaffDao(context);
                dao.open();
                Staff staff5 = dao.findStaff(5);

            }
        });
    }

}
