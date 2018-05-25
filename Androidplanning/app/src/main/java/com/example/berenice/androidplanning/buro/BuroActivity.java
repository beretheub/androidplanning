package com.example.berenice.androidplanning.buro;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.berenice.androidplanning.R;
import com.example.berenice.androidplanning.database.Staff;
import com.example.berenice.androidplanning.database.StaffDao;

import java.util.ArrayList;

public class BuroActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buro);

        //Find list of the entire staff
        StaffDao sdao = new StaffDao(this);
        sdao.open();
        ArrayList<Staff> allStaff = sdao.findAllStaff();
        sdao.close();

        //Instantiate adapter
        final StaffAdapter adapter = new StaffAdapter(allStaff, this);

        //handle listview and assign adapter
        ListView staffList = findViewById(R.id.listStaff);
        staffList.setAdapter(adapter);

        Button checkAll;
        Button unCheckAll;

        checkAll = findViewById(R.id.selectAllButton);
        unCheckAll = findViewById(R.id.unselectAllButton);

        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setAllChecked(true);
            }
        });

        unCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setAllChecked(false);
            }
        });
    }

}
