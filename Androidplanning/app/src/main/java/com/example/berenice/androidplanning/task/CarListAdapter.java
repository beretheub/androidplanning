package com.example.berenice.androidplanning.task;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
 *
 */
public class CarListAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Staff> list;
    private Context context;
    private Staff driver;

    public CarListAdapter(ArrayList<Staff> list, Staff driver, Context context) {
        this.list = list;
        this.context = context;
        this.driver = driver;
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
            view = inflater.inflate(R.layout.line_cocar, null);
        }
        //Handle TextView and display string from your list
        TextView listItemText = (TextView) view.findViewById(R.id.nameCoCar);
        listItemText.setText(list.get(position).getFirstname() + " " +list.get(position).getName());
        String name = list.get(position).getName();
        if(getItemId(position) == driver.getId()){
            listItemText.setTextColor(Color.RED);
        }
        else{
            listItemText.setTextColor(Color.BLACK);
        }

        return view;
    }
}
