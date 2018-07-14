package com.example.jason.visitorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jason.visitorapp.Adapters.VisitorsListAdapter;

public class Vistors extends AppCompatActivity {
RecyclerView visitorsList;
VisitorsListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistors);
        visitorsList = findViewById(R.id.visitorsList);
        adapter = new VisitorsListAdapter(getApplicationContext());
        visitorsList.setLayoutManager(new GridLayoutManager(this,3));
        visitorsList.setAdapter(adapter);
    }
}
