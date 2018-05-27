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
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.berenice.androidplanning.R;
import com.example.berenice.androidplanning.database.QueryHandler;
import com.example.berenice.androidplanning.database.Staff;
import com.example.berenice.androidplanning.database.StaffDao;
import com.example.berenice.androidplanning.sendSms.SendSmsActivity;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Activity to enable the buro to contact a group of staffeurs
 **/
public class BuroActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buro);

        android.support.v7.widget.Toolbar toolbar =
                (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //Find list of the entire staff
        StaffDao sdao = new StaffDao(this);
        sdao.open();
        final ArrayList<Staff> allStaff = sdao.findAllStaff();
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
        final CheckBox onlyRaceCheckBox;

        checkAll = findViewById(R.id.selectAllButton);
        unCheckAll = findViewById(R.id.unselectAllButton);
        sendMessage = findViewById(R.id.sendMessageSelected);
        onlyRaceCheckBox = findViewById(R.id.onlyRaceCheckBox);

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
                ArrayList<Staff> staffChecked = adapter.getItemsChecked();
                if(staffChecked.size()==0){
                    Toast.makeText(getApplicationContext(),
                            "Please choose at least one recipient", Toast.LENGTH_SHORT).show();
                    return;
                }
                Collections.sort(staffChecked);
                String recipsId="";
                for (Staff s: staffChecked){
                    recipsId += String.valueOf(s.getId())+",";
                }
                Intent i = new Intent(BuroActivity.this, SendSmsActivity.class);
                i.putExtra("recips",recipsId);
                startActivity(i);
            }
        });

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);

        SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextChange(String cs)
            {
                adapter.getFilter().filter(cs);
                //adapter.setTextToFilter(cs);
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query)
            {
                adapter.getFilter().filter(query);
                //adapter.setTextToFilter(query);
                return false;
            }
        };
        searchView.setOnQueryTextListener(textChangeListener);

        onlyRaceCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onlyRaceCheckBox.isChecked()){
                    QueryHandler qh = new QueryHandler();
                    ArrayList<Staff> staffOnRace = qh.getStaffOnRace(BuroActivity.this);
                    adapter.setList(staffOnRace);
                }
                else{
                    adapter.setList(allStaff);
                }
                searchView.setQuery("",false);
            }
        });
    }

}
