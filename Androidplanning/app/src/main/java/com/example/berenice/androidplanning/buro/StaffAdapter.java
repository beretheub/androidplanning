package com.example.berenice.androidplanning.buro;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.berenice.androidplanning.R;
import com.example.berenice.androidplanning.database.Staff;
import com.example.berenice.androidplanning.sendSms.SendSmsActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * ListAdapter in order to show the colleagues on a certain task
 */
public class StaffAdapter extends BaseAdapter implements ListAdapter{

    private ArrayList<Staff> list = new ArrayList<>();
    private Context context;
    private ArrayList<Integer> positionsChecked = new ArrayList<>();

    private boolean allChecked;

    public StaffAdapter(ArrayList<Staff> list, Context context) {
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

    public void setAllChecked(boolean value){
        positionsChecked = new ArrayList<>();
        allChecked=value;
        notifyDataSetChanged();
        if(allChecked) {
            for (int i=0; i<list.size();i++){
                positionsChecked.add(i);
            }
        }
    }

    public ArrayList<Integer> getPositionsChecked(){
        return positionsChecked;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.simple_list_multiple_choice, null);
        }

        final CheckedTextView nameView = view.findViewById(R.id.text1);
        nameView.setText(getItem(position).getName());
        nameView.setChecked(allChecked);


        nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CheckedTextView)v).toggle();
                if(nameView.isChecked()){
                    positionsChecked.add(position);
                }
                else {
                    for(int i=0; i<positionsChecked.size(); i++){
                        if (positionsChecked.get(i)==position){positionsChecked.remove(i);}
                    }
                }

            }
        });

        return view;
    }



}
