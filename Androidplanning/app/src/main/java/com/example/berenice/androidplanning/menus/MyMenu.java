package com.example.berenice.androidplanning.menus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.berenice.androidplanning.R;
import com.example.berenice.androidplanning.buro.BuroActivity;
import com.example.berenice.androidplanning.database.TestDbActivity;
import com.example.berenice.androidplanning.schedule.ScheduleActivity;
import com.example.berenice.androidplanning.sendSms.SendSmsActivity;
import com.example.berenice.androidplanning.task.taskActivity;

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

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MyMenu extends AppCompatActivity {
    Button Close;
    public boolean onclickAction(MenuItem item, Context context)
    {
        Intent i;

        switch (item.getItemId()){
            case R.id.choose_day:
                showpopupMenu();
                return true;

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


    private PopupWindow pw;

    private void showpopupMenu() {
        try {
// We need to get the instance of the LayoutInflater
            Context mContext = this.getBaseContext();
            LayoutInflater inflater = (LayoutInflater)
                    mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_menu,
                    (ViewGroup) findViewById(R.id.popup_menu));
            pw = new PopupWindow(layout, 900, 1270, true);
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);

            Close = (Button) layout.findViewById(R.id.close_popup_menu);
            Close.setOnClickListener(cancel_button);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RadioButton b1 =(RadioButton) findViewById(R.id.butt1);
        b1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
/*                //Do Something
                Intent intent = new Intent(MyMenu.this, MainActivity.class);
                intent.putExtra("currentTask", String.valueOf(list.get(position).getId()));
                startActivity(intent);
                //Close Window
                POPUP_WINDOW_SCORE.dismiss();*/
            }
        });


    }

    private View.OnClickListener cancel_button = new View.OnClickListener() {
        public void onClick(View v) {
            pw.dismiss();
        }
    };
}
