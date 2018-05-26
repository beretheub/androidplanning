package com.example.berenice.androidplanning.buro;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.berenice.androidplanning.R;
import com.example.berenice.androidplanning.database.Staff;
import com.example.berenice.androidplanning.database.StaffDao;
import com.example.berenice.androidplanning.sendSms.SendSmsActivity;

import java.util.ArrayList;
import java.util.Collections;

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
        final ListView staffList = findViewById(R.id.listStaff);
        staffList.setAdapter(adapter);
        //staffList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);


        Button checkAll;
        Button unCheckAll;
        Button sendMessage;

        checkAll = findViewById(R.id.selectAllButton);
        unCheckAll = findViewById(R.id.unselectAllButton);
        sendMessage = findViewById(R.id.sendMessageSelected);

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

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> positionsChecked = adapter.getPositionsChecked();
                if(positionsChecked.size()==0){
                    Toast.makeText(BuroActivity.this,
                            "Please choose at least one recipient", Toast.LENGTH_SHORT).show();
                    return;
                }
                Collections.sort(positionsChecked);
                String recipsId="";
                for (int i: positionsChecked){
                    recipsId += String.valueOf(i+1)+",";
                }
                Intent i = new Intent(BuroActivity.this, SendSmsActivity.class);
                i.putExtra("recips",recipsId);
                startActivity(i);
            }
        });

    }

}
