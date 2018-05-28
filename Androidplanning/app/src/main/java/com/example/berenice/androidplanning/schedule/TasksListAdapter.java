package com.example.berenice.androidplanning.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.berenice.androidplanning.DateFormater;
import com.example.berenice.androidplanning.R;
import com.example.berenice.androidplanning.database.Task;

import java.util.ArrayList;
/**
 * ListAdapter in order to show the different tasks that a staffeur has
 */
public class TasksListAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<Task> list = new ArrayList<Task>();
    private Context context;

    public TasksListAdapter(ArrayList<Task> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public Task getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int pos) {
        //TODO check how to find out which button has been pressed
        return getItem(pos).getId();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.line_task, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView) view.findViewById(R.id.nameTask_schedule);
        listItemText.setText(list.get(position).getName());

        TextView departure = (TextView)view.findViewById(R.id.departureTime_schedule);
        departure.setText(DateFormater.formatDateToTime(list.get(position).getDeparture()));

        TextView begin = (TextView)view.findViewById(R.id.beginTime_schedule);
        begin.setText(DateFormater.formatDateToTime(list.get(position).getBegin()));

        TextView end = (TextView)view.findViewById(R.id.endTime_schedule);
        end.setText(DateFormater.formatDateToTime(list.get(position).getEnd()));

        TextView description = (TextView)view.findViewById(R.id.descriptionTask_schedule);
        description.setText(list.get(position).getDescription());


        return view;
    }
}