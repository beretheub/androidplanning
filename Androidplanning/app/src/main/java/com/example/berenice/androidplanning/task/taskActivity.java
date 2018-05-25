package com.example.berenice.androidplanning.task;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.berenice.androidplanning.DateFormater;
import com.example.berenice.androidplanning.MyMenu;
import com.example.berenice.androidplanning.R;
import com.example.berenice.androidplanning.database.Car;
import com.example.berenice.androidplanning.database.CarDao;
import com.example.berenice.androidplanning.database.QueryHandler;
import com.example.berenice.androidplanning.database.Staff;
import com.example.berenice.androidplanning.database.Task;
import com.example.berenice.androidplanning.database.TaskDao;
import com.example.berenice.androidplanning.sendSms.SendSmsActivity;

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

        //look up the current userid
        final int userID;
        SharedPreferences prefs = getSharedPreferences("PlanningPreferences", MODE_PRIVATE);
        userID = prefs.getInt("userID", 0);

        //get task id from intent
        int taskID;
        taskID = Integer.parseInt(getIntent().getStringExtra("currentTask"));

        //get task info from database
        TaskDao dao = new TaskDao(getBaseContext());
        dao.open();
        Task currentTask = dao.findTask(taskID);
        dao.close();

        //Update fields
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle(currentTask.getName());

        TextView departureTime = findViewById(R.id.departureTime_task);
        departureTime.setText(DateFormater.formatDateToTime(currentTask.getDeparture()));
        TextView startTime = findViewById(R.id.beginTime);
        startTime.setText(DateFormater.formatDateToTime(currentTask.getBegin()));
        TextView endTime = findViewById(R.id.endTime);
        endTime.setText(DateFormater.formatDateToTime(currentTask.getEnd()));

        TextView descriptionText = findViewById(R.id.descriptionText);
        descriptionText.setText(currentTask.getDescription());

        //COSTAFF ADAPTER
        //lookup the colleagues in the database
        QueryHandler qh = new QueryHandler();
        final ArrayList<Staff> costaff = qh.getCostaff(getBaseContext(), taskID);

        //instantiate costaff adapter
        CostaffListAdapter costaffAdapter = new CostaffListAdapter(costaff, this);

        //handle listview and assign adapter
        ListView costaffListView = (ListView)findViewById(R.id.listCostaff);
        costaffListView.setAdapter(costaffAdapter);

        //COCAR ADAPTER
        //Database searches
        final ArrayList<Object> car_driver= qh.getCarDriver(getBaseContext(), taskID, userID);
        if (car_driver != null) {
            Car currentCar = (Car) car_driver.get(0);
            Staff driver = (Staff) car_driver.get(1);
            final ArrayList<Staff> passengers = qh.getPassengersCar(getBaseContext(), currentCar.getId());

            //instantiate adapter
            CarListAdapter carAdapter = new CarListAdapter(passengers, driver, this);

            //handle listview and assign adapter
            ListView passengersListView = (ListView) findViewById(R.id.passengerList);
            passengersListView.setAdapter(carAdapter);

            //Write Car name
            TextView carName = (TextView) findViewById(R.id.carName);
            carName.setText(currentCar.getName());
        }

        //Listener for messages to all
        Button sendMessageToCostaff = findViewById(R.id.sendMessageToCostaff);
        sendMessageToCostaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recips="";
                for (Staff s : costaff)
                {
                    //add all the ids of other costaff to a string
                    if (s.getId()!=userID){
                        recips+=s.getId()+",";}
                }
                Intent i = new Intent(getBaseContext(), SendSmsActivity.class);
                i.putExtra("recips",recips);
                startActivity(i);
            }
        });

        //Ask for Call permission
        if (!(ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    0);
        }
    }

}
