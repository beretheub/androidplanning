package com.example.berenice.androidplanning.task;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.berenice.androidplanning.R;
import com.example.berenice.androidplanning.database.Staff;
import com.example.berenice.androidplanning.sendSms.SendSmsActivity;

import java.util.ArrayList;

/**
 * ListAdapter in order to show the colleagues on a certain task
 */
public class CostaffListAdapter extends BaseAdapter implements ListAdapter{

    private ArrayList<Staff> list = new ArrayList<>();
    private Context context;

    public CostaffListAdapter(ArrayList<Staff> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Staff getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {

        return getItem(pos).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.line_costaff, null);
        }
        //Handle TextView and display string from your list
        TextView listItemText = (TextView) view.findViewById(R.id.nameCostaff);
        listItemText.setText(getItem(position).getName()+", "+getItem(position).getFirstname());

        //Handle buttons and add onClickListeners
        ImageButton callBtn = (ImageButton) view.findViewById(R.id.call_btn);
        ImageButton smsBtn = (ImageButton) view.findViewById(R.id.sms_btn);

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+getItem(position).getPhonenumber()));
                try{context.startActivity(callIntent);}
                catch(SecurityException e){
                    Toast.makeText(context, "No permission", Toast.LENGTH_SHORT).show();
                }
            }
        });
        smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(context, SendSmsActivity.class);
                smsIntent.putExtra("recips",String.valueOf(getItem(position).getId()));
                context.startActivity(smsIntent);
            }
        });

        return view;
    }

    }
