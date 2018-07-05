package com.example.jason.visitorapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.jason.visitorapp.R;
import com.example.jason.visitorapp.modals.Staff;

import java.util.ArrayList;
import java.util.List;

public class StaffListAdapter extends ArrayAdapter<Staff> {

    private ArrayList<Staff> staffListfull;
    public StaffListAdapter(@NonNull Context context, @NonNull ArrayList<Staff> staffList) {
        super(context,0, staffList);
        staffListfull = staffList;

    }


    @NonNull
    @Override

    public Filter getFilter() {
        return staffFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.staff_list,parent,false);
        }
        TextView department = convertView.findViewById(R.id.department);
        TextView name = convertView.findViewById(R.id.staffname);
        Staff staff = getItem(position);

        if(staff !=null){
            department.setText(staff.getDepartment());
            name.setText(staff.getEmployeeName());

        }
        return convertView;
    }

    private Filter staffFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            List<Staff> suggestion = new ArrayList<>();
            if(charSequence == null || charSequence.length() ==0){
                suggestion.addAll(staffListfull);
            }else {
                String filterPatter = charSequence.toString().trim().toLowerCase();
                for (Staff staff1 : staffListfull){
                    if(staff1.getEmployeeName().toLowerCase().contains(filterPatter)){
                        suggestion.add(staff1);
                    }
                }

            }
            results.values =suggestion;
            results.count = suggestion.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            clear();
            addAll((List)filterResults.values);
            notifyDataSetChanged();

        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Staff) resultValue).getEmployeeName();
        }
    };
}
