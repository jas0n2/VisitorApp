package com.example.jason.visitorapp.Adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jason.visitorapp.R;
import com.example.jason.visitorapp.modals.Visitors;
import com.example.jason.visitorapp.modals.visitorsModel;

import java.util.ArrayList;

public class VisitorsListAdapter extends RecyclerView.Adapter<Viewholder> {
    private Context context;
    private ArrayList<Visitors> visitorsArrayList;

    public VisitorsListAdapter(Context context) {
        this.context = context;
        visitorsArrayList = visitorsModel.getVisitorsModel(context).getVisitorsList();
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylist_view,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        Visitors visitors = visitorsArrayList.get(position);
        holder.intializeView(visitors);
    }



    @Override
    public int getItemCount() {
        return 0;
    }
}


class Viewholder extends RecyclerView.ViewHolder{
AppCompatTextView date,name,timin,timout,visiting,address,from;
AppCompatButton signout;
    public Viewholder(View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.date);
        name = itemView.findViewById(R.id.date);
        timin = itemView.findViewById(R.id.tii);
        timout = itemView.findViewById(R.id.tio);
        visiting = itemView.findViewById(R.id.visiting);
        address = itemView.findViewById(R.id.address);
        from = itemView.findViewById(R.id.from);

    }
    public void intializeView(Visitors visitors){
   date.setText(visitors.getDate());
        name.setText("kk");
        timout.setText(visitors.getTimeOut());
        timin.setText(visitors.getTimeIn());
        visiting.setText(visitors.getVisitee());
        address.setText(visitors.getLocationAddress());
        from.setText(visitors.getLocationType());

    }
}