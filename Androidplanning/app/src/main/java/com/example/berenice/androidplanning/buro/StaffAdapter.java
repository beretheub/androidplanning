package com.example.berenice.androidplanning.buro;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.Filter;
import android.widget.Filterable;
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
public class StaffAdapter extends BaseAdapter implements ListAdapter, Filterable{

    private ArrayList<Staff> list = new ArrayList<>();
    private ArrayList<Staff> filteredList = new ArrayList<>();
    private Context context;
    private ArrayList<Staff> itemsChecked = new ArrayList<>();

    private boolean allChecked;

    public StaffAdapter(ArrayList<Staff> list, Context context) {
        this.list = list;
        this.filteredList = new ArrayList<>(list);
        this.context = context;
    }

    public void setList(ArrayList<Staff> list){
        this.list = list;
        this.filteredList = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Staff getItem(int pos) {
        if (filteredList.size()<=pos)
            return null;
        return filteredList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        if (filteredList.size()<=pos)
            return 0;
        return getItem(pos).getId();
    }

    public void setAllChecked(boolean value){
        itemsChecked = new ArrayList<>();
        allChecked=value;
        notifyDataSetChanged();
        if(allChecked) {
            itemsChecked = new ArrayList<>(filteredList);
        }
    }

    public ArrayList<Staff> getItemsChecked(){
        return itemsChecked;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.simple_list_multiple_choice, null);
        }

        final CheckedTextView nameView = view.findViewById(R.id.text1);
        if (filteredList.size() == 0 || position >= filteredList.size()){
            nameView.setVisibility(View.INVISIBLE);
            return view;
        }

        nameView.setVisibility(View.VISIBLE);

        nameView.setText(getItem(position).getName()+ ", " + getItem(position).getFirstname());
        nameView.setChecked(itemsChecked.contains(getItem(position)));


        nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CheckedTextView)v).toggle();
                if(nameView.isChecked()){
                    itemsChecked.add(filteredList.get(position));
                }
                else {
                    itemsChecked.remove(filteredList.get(position));
                }

            }
        });

        return view;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                // Create a FilterResults object
                FilterResults results = new FilterResults();

                // If the constraint (search string/pattern) is null
                // or its length is 0, i.e., its empty then
                // we just set the `values` property to the
                // original contacts list which contains all of them
                if (constraint == null || constraint.length() == 0) {
                    results.values = list;
                    results.count = list.size();
                }

                else {
                    // Some search constraint has been passed
                    // so let's filter accordingly
                    ArrayList<Staff> filteredContacts = new ArrayList<Staff>();

                    // We'll go through all the contacts and see
                    // if they contain the supplied string
                    for (Staff s : list) {
                        if ((s.getName()+" "+s.getFirstname()).toUpperCase()
                                .contains(constraint.toString().toUpperCase() ))
                        {
                            // if `contains` == true then add it
                            // to our filtered list
                            filteredContacts.add(s);
                        }
                    }

                    // Finally set the filtered values and size/count
                    results.values = filteredContacts;
                    results.count = filteredContacts.size();
                }

                // Return our FilterResults object
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (ArrayList<Staff>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
