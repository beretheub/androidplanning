package com.example.berenice.androidplanning.task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.berenice.androidplanning.R;

import java.util.ArrayList;

/**
 * ListAdapter in order to show the colleagues on a certain task
 */
public class CostaffListAdapter extends BaseAdapter implements ListAdapter{

    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public CostaffListAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        //TODO check how to find out which button has been pressed
        return 0;
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
        listItemText.setText(list.get(position));


        //Handle buttons and add onClickListeners
        Button callBtn = (Button) view.findViewById(R.id.call_btn);
        Button smsBtn = (Button) view.findViewById(R.id.sms_btn);

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: find out who was clicked and call him
            }
        });
        smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: find out who was clicked and message him
            }
        });

        return view;
    }

    }
