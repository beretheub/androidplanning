package com.example.berenice.androidplanning;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.berenice.androidplanning.buro.BuroActivity;
import com.example.berenice.androidplanning.database.TestDbActivity;
import com.example.berenice.androidplanning.records.ScheduleActivity;
import com.example.berenice.androidplanning.sendSms.SendSmsActivity;
import com.example.berenice.androidplanning.task.taskActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MyMenu {
    public boolean onclickAction(MenuItem item, Context context)
    {
        Intent i;
        switch (item.getItemId()){
            case R.id.goto_sendSms:
                i = new Intent(context, SendSmsActivity.class);
                i.putExtra("recips","5,6,28");
                context.startActivity(i);
                return true;

            case R.id.goto_task:
                i = new Intent(context, taskActivity.class);
                //TODO just to test!
                i.putExtra("currentTask", "28");
                context.startActivity(i);
                return true;

            case R.id.goto_schedule:
                i = new Intent(context, ScheduleActivity.class);
                context.startActivity(i);
                return true;

            case R.id.goto_testDB:
                i = new Intent(context, TestDbActivity.class);
                context.startActivity(i);
                return true;

            case R.id.goto_buro:
                i = new Intent(context, BuroActivity.class);
                context.startActivity(i);
                return true;

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
