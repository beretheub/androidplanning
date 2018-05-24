package com.example.berenice.androidplanning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.berenice.androidplanning.database.Staff;
import com.example.berenice.androidplanning.database.StaffDao;
import com.example.berenice.androidplanning.database.TestDbActivity;
import com.example.berenice.androidplanning.records.RecordActivity;
import com.example.berenice.androidplanning.sendSms.SendSmsActivity;
import com.example.berenice.androidplanning.task.taskActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton nameButton = (ImageButton) findViewById(R.id.nameButton);
        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get name from EditText
                EditText edit = (EditText)findViewById(R.id.nom);
                String name = edit.getText().toString();

                //Find the data of the staff in the database
                StaffDao dao = new StaffDao(getBaseContext());
                dao.open();
                Staff staff = dao.findStaffByName(name);
                if (staff == null){
                    Toast.makeText(MainActivity.this,"Staff not fund",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                int userID = staff.getId();

                //Save id in the preferences
                SharedPreferences.Editor editor = getSharedPreferences("PlanningPreferences", MODE_PRIVATE).edit();
                editor.putInt("userID", userID);
                editor.putString("Day", "4");
                editor.apply();

                //Open Record activity
                Intent i = new Intent(getBaseContext(), RecordActivity.class);
                startActivity(i);
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
        return new MyMenu().onclickAction(item, this, getBaseContext());
    }

}
