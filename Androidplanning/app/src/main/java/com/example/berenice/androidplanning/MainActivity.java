package com.example.berenice.androidplanning;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.berenice.androidplanning.database.Constants;
import com.example.berenice.androidplanning.database.DatabaseHandler;
import com.example.berenice.androidplanning.database.ImportHandler;
import com.example.berenice.androidplanning.database.Staff;
import com.example.berenice.androidplanning.database.StaffDao;
import com.example.berenice.androidplanning.menus.MyMenu;
import com.example.berenice.androidplanning.schedule.ScheduleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check if there is a database
        if(!DatabaseHandler.doesDatabaseExist(this, Constants.DATABASE_NAME)){
            ImportHandler ih = new ImportHandler(this);
            ih.importAll("4");
        }


        //find names of all the staff
        final StaffDao dao = new StaffDao(this);
        dao.open();
        final String[] staffNames = dao.findAllStaffNames();
        final ArrayAdapter<String> namesAdapater = new ArrayAdapter<String>
                (this, R.layout.dropdown, staffNames);

        //set names in autocomplete textview
        final AutoCompleteTextView nameField = (AutoCompleteTextView) findViewById(R.id.nameField);
        nameField.setThreshold(1);
        nameField.setAdapter(namesAdapater);

        //find currentStaff if there is one
        SharedPreferences prefs =
                getSharedPreferences("PlanningPreferences", MODE_PRIVATE);
        int currentID = prefs.getInt("userID",0);
        if (currentID != 0){
            Staff currentStaff = dao.findStaffById(currentID);
            nameField.setHint(currentStaff.getFirstname() + " " + currentStaff.getName());
        }

        //Safe name only when user picks a value
        nameField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String input = namesAdapater.getItem(position).toString();

                Staff currentPerson = dao.findStaffByName(input.split(" ")[1]);

                SharedPreferences.Editor editor =
                        getSharedPreferences("PlanningPreferences", MODE_PRIVATE).edit();
                editor.putInt("userID", currentPerson.getId());
                editor.apply();
            }
        });

        //when the user types, check if the current input is a legit input,
        //in that case safe it directly
        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SharedPreferences.Editor editor;
                for (String name:staffNames)
                {
                    if (s.toString().equalsIgnoreCase(name)){
                         editor = getSharedPreferences
                                 ("PlanningPreferences", MODE_PRIVATE).edit();
                         editor.putInt("userID",
                                 dao.findStaffByName(name.split(" ")[1]).getId());
                         editor.apply();
                    }
                }

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        final ImageButton nameButton = (ImageButton) findViewById(R.id.nameButton);
        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check if user has already entered something legit
                SharedPreferences prefs =
                        getSharedPreferences("PlanningPreferences", MODE_PRIVATE);
                if (prefs.getInt("userID",0)==0) {
                    Toast.makeText(MainActivity.this,
                            "\"Please identify yourself!\"", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Open Record activity
                Intent i = new Intent(getBaseContext(), ScheduleActivity.class);
                startActivity(i);
            }
        });

        final Button openDayDialog = (Button) findViewById(R.id.openDayDialog);
        openDayDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.popup_menu, null);
                dialogBuilder.setView(dialogView);

                final AlertDialog alertDialog = dialogBuilder.create();

                final RadioGroup group =(RadioGroup) dialogView.findViewById(R.id.pickDay);
                Button okButton = (Button) dialogView.findViewById(R.id.close_popup_menu);

                okButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        //Find current day
                        int radioButtonId = group.getCheckedRadioButtonId();
                        if(radioButtonId==-1){
                            Toast.makeText(MainActivity.this,
                                    "Please choose the current day", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        RadioButton selected=
                                (RadioButton) group.findViewById(radioButtonId);
                        String buttonText = (String) selected.getText();
                        String newDay =
                                buttonText.substring(buttonText.length()-1);

                        //Save current day in preferences
                        SharedPreferences.Editor editor = getSharedPreferences
                                ("PlanningPreferences", MODE_PRIVATE).edit();
                        editor.putString("Day", newDay);
                        editor.apply();

                        //Load the right database
                        ImportHandler ih = new ImportHandler(MainActivity.this);
                        ih.importAll(newDay);

                        //Closes popup
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return new MyMenu().onCreate(menu, this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return new MyMenu().onclickAction(item, getBaseContext());
    }



}
