package com.example.berenice.androidplanning.sendSms;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.service.carrier.CarrierMessagingService;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.berenice.androidplanning.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class sendSmsActivity extends AppCompatActivity {

    ListView recipientsListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        if (!(ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    0);
        }

        final Context myContext = this.getApplicationContext();

        recipientsListView = (ListView) findViewById(R.id.listRecipients);

        //TODO here only test values until now, will one day be populated by intent
        final String[][] recips = new String[][]{
                {"Till", "0769422814"},
                {"Béré", "0769422814"}
        };

        List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();

        HashMap<String,String> element;
        for(int i = 0; i < recips.length; i++){
            element = new HashMap<String, String>();
            element.put("name", recips[i][0]);
            element.put("number", recips[i][1]);
            list.add(element);
        }

        ListAdapter adapter = new SimpleAdapter(this,
                list,
                android.R.layout.simple_list_item_2,
                new String[] {"name", "number"},
                new int[] {android.R.id.text1, android.R.id.text2 });

        recipientsListView.setAdapter(adapter);

        Button button = findViewById(R.id.buttonSend);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumbers = "";
                for(int i = 0; i < recips.length; i++)
                {
                    phoneNumbers += recips[i][1] + ",";
                }
                phoneNumbers = phoneNumbers.
                        substring(0,phoneNumbers.length()-1);
                EditText edit = (EditText)findViewById(R.id.editTextMessage);
                new Sms().sendSMS(phoneNumbers,edit.getText().toString(),myContext);
            }
        });
    }
}
