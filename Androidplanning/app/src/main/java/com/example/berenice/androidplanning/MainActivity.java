package com.example.berenice.androidplanning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import com.example.berenice.androidplanning.task.taskActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Salut

        Button smsButton = findViewById(R.id.goToSmsButton);
        smsButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getBaseContext(), com.example.berenice.androidplanning.sendSms.sendSmsActivity.class);
                startActivity(i);
            }
        });

        Button taskButton = findViewById(R.id.goToTaskButton);
        taskButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getBaseContext(), taskActivity.class);
                startActivity(i);
            }
        });
    }
}
