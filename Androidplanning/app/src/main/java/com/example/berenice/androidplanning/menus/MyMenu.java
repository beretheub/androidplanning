package com.example.berenice.androidplanning.menus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.berenice.androidplanning.R;
import com.example.berenice.androidplanning.buro.LoginActivity;
import com.example.berenice.androidplanning.schedule.ScheduleActivity;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

/**
 * Define the menu that is used for some activities
 **/
public class MyMenu extends AppCompatActivity {
    Button Close;
    public boolean onclickAction(MenuItem item, Context context)
    {
        Intent i;

        switch (item.getItemId()){
            case R.id.goto_schedule:
                i = new Intent(context, ScheduleActivity.class);
                context.startActivity(i);
                return true;

            case R.id.goto_buro:
                i = new Intent(context, LoginActivity.class);
                context.startActivity(i);
                return true;
            /*
            //Used for tests during developement
            case R.id.goto_sendSms:
                i = new Intent(context, SendSmsActivity.class);
                i.putExtra("recips","5,6,28");
                context.startActivity(i);
                return true;

            case R.id.goto_task:
                i = new Intent(context, taskActivity.class);
                i.putExtra("currentTask", "28");
                context.startActivity(i);
                return true;

            case R.id.goto_testDB:
                i = new Intent(context, TestDbActivity.class);
                context.startActivity(i);
                return true;
            */

            default:
                return false;
        }
    }

    public boolean onCreate(Menu menu, Activity motherAct){
        MenuInflater inflater = motherAct.getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
