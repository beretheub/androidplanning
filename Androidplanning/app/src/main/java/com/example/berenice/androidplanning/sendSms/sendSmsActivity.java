package com.example.berenice.androidplanning.sendSms;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.berenice.androidplanning.R;
import com.example.berenice.androidplanning.database.Staff;
import com.example.berenice.androidplanning.database.StaffDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Activity to write and send a SMS to predefined persons
 * Takes the ids of recipients in the intent
 */
public class SendSmsActivity extends AppCompatActivity  {

    ListView recipientsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle(R.string.title_activity_send_sms);

        //Ask for SMS permission
        if (!(ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    0);
        }

        final Context myContext = this.getApplicationContext();

        recipientsListView = (ListView) findViewById(R.id.listRecipients);

        final ArrayList<Staff> recips = new ArrayList<>();
        String recipsIDs = getIntent().getStringExtra("recips");
        if (recipsIDs!=""){
            String[] ids = recipsIDs.split(",");
            StaffDao dao = new StaffDao(getBaseContext());
            dao.open();
            for (int i = 0; i<ids.length; i++){
                recips.add(dao.findStaffById(Integer.parseInt(ids[i])));
            }
            dao.close();
        }

        List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();

        HashMap<String,String> element;
        for(Staff s:recips){
            element = new HashMap<String, String>();
            element.put("name", s.getName()+", "+s.getFirstname());
            element.put("number", s.getPhonenumber());
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
                for(Staff s:recips)
                {
                    phoneNumbers += s.getPhonenumber() + ",";
                }
                phoneNumbers = phoneNumbers.
                        substring(0,phoneNumbers.length()-1);
                EditText edit = (EditText)findViewById(R.id.editTextMessage);
                new Sms().sendSMS(phoneNumbers,edit.getText().toString(),myContext);
            }
        });
    }
}
