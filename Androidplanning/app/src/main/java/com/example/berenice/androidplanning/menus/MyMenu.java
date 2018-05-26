package com.example.berenice.androidplanning.menus;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.berenice.androidplanning.MainActivity;
import com.example.berenice.androidplanning.R;
import com.example.berenice.androidplanning.buro.BuroActivity;
import com.example.berenice.androidplanning.database.ImportHandler;
import com.example.berenice.androidplanning.database.TestDbActivity;
import com.example.berenice.androidplanning.schedule.ScheduleActivity;
import com.example.berenice.androidplanning.sendSms.SendSmsActivity;
import com.example.berenice.androidplanning.task.taskActivity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MyMenu extends AppCompatActivity {
    Button Close;
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
