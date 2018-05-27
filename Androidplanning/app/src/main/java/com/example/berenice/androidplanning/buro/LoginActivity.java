package com.example.berenice.androidplanning.buro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.berenice.androidplanning.R;

/**
 * Enable to login as a buro member
 **/

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        android.support.v7.widget.Toolbar toolbar =
                (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        // Initialization
        username = (EditText) findViewById(R.id.et_Username);
        password = (EditText) findViewById(R.id.et_Password);
        signIn = (Button) findViewById(R.id.bt_SignIn);

        signIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Stores User name
                String username = String.valueOf(LoginActivity.this.username.getText());
                // Stores Password
                String password = String.valueOf(LoginActivity.this.password.getText());

                // Validates the User name and Password for admin, admin
                if (username.equals("buro") && password.equals("buro")) {
                    Intent i = new Intent(LoginActivity.this, BuroActivity.class);
                    startActivity(i);
                    return;
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Credentials invalid.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
