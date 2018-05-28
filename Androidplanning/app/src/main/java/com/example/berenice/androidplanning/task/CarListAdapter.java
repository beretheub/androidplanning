package com.example.berenice.androidplanning.task;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.berenice.androidplanning.R;
import com.example.berenice.androidplanning.database.Staff;

import java.util.ArrayList;

/**
 * Adapter to display the composition of a car
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
