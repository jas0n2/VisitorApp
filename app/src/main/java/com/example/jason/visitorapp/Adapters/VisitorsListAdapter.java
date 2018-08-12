package com.example.jason.visitorapp.Adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.jason.visitorapp.Helpers.SQliteHelper;
import com.example.jason.visitorapp.R;
import com.example.jason.visitorapp.modals.Visitors;
import com.example.jason.visitorapp.modals.visitorsModel;
import com.example.jason.visitorapp.util.GlobalVariables;

import java.util.ArrayList;
import java.util.Map;

import static com.example.jason.visitorapp.Adapters.VisitorsListAdapter.TYPE_HEAD;
import static com.example.jason.visitorapp.Adapters.VisitorsListAdapter.TYPE_LIST;

public class VisitorsListAdapter extends RecyclerView.Adapter<Viewholder> {
    private Context context;
      ArrayList<Visitors> visitorsArrayList;
     static final int TYPE_HEAD= 0;
     static final int TYPE_LIST= 1;
    public VisitorsListAdapter(Context context) {
        this.context = context;
        visitorsArrayList = visitorsModel.getVisitorsModel(context).getVisitorsList();
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
if(viewType == TYPE_LIST){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylist_view,parent,false);
        return new Viewholder(view,viewType,context);
    }else  if(viewType == TYPE_HEAD) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_layout,parent,false);
    return new Viewholder(view,viewType,context);
}

return  null;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {

        if(holder.view_Type == TYPE_LIST){
            Visitors visitors = visitorsArrayList.get(position-1);
            holder.intializeView(visitors);
        }else if(holder.view_Type == TYPE_HEAD){
            holder.intializeView2();
        }

    }



    @Override
    public int getItemCount() {
        return visitorsArrayList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position ==0)
            return TYPE_HEAD;
            return TYPE_LIST;


        }
    public void setFilter(ArrayList<Visitors>neList){
     visitorsArrayList = new ArrayList<>();
     visitorsArrayList.addAll(neList);
     notifyDataSetChanged();

    }
}


  class Viewholder extends RecyclerView.ViewHolder{
    AppCompatTextView date,name,timin,timout,visiting,address,from;
    AppCompatTextView dateh,nameh,timinh,timouth,visitingh,addressh,fromh;
    AppCompatButton singin;

int view_Type;
Context context;

    public Viewholder(View itemView, int viewType, final Context context) {
        super(itemView);
        this.context = context;

        if(viewType ==TYPE_LIST){
            date = itemView.findViewById(R.id.date);
            name = itemView.findViewById(R.id.name);
            timin = itemView.findViewById(R.id.tii);
            timout = itemView.findViewById(R.id.tio);
            visiting = itemView.findViewById(R.id.visiting);
            address = itemView.findViewById(R.id.address);
            from = itemView.findViewById(R.id.from);
            singin = itemView.findViewById(R.id.signbtn);
            view_Type = 1;
            //Visitors visitors = (Visitors) itemView.


        }else if(viewType == TYPE_HEAD){
            dateh = itemView.findViewById(R.id.hdate);
            nameh = itemView.findViewById(R.id.hname);
            timinh = itemView.findViewById(R.id.htii);
            timouth = itemView.findViewById(R.id.htio);
            visitingh = itemView.findViewById(R.id.hvisiting);
            addressh = itemView.findViewById(R.id.haddress);
            fromh = itemView.findViewById(R.id.hfrom);
            view_Type = 0;


        }


    }
    public void intializeView(final Visitors visitors){
   date.setText(visitors.getDate());
   date.setText(visitors.getDate());
        name.setText(visitors.getName());
        timout.setText(visitors.getTimeOut());
        timin.setText(visitors.getTimeIn());
        visiting.setText(visitors.getVisitee());
        address.setText(visitors.getLocationAddress());
        from.setText(visitors.getLocationType());
        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQliteHelper helper = new SQliteHelper(context);
                boolean updated = helper.signoutQuery(visitors.getID());
                if(updated) {
                    StringRequest request = new StringRequest(Request.Method.POST, GlobalVariables.udateStatus, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            return super.getParams();
                        }
                    };
                    Toast.makeText(context, String.valueOf(visitors.getID()), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    public void intializeView2(){
     dateh.setText("Date");
        nameh.setText("Name");
        timouth.setText("Status");
        timinh.setText("Time In");
        visitingh.setText("Visiting");
        addressh.setText("Address");
        fromh.setText("From");

    }


}