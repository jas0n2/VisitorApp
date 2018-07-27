package com.example.jason.visitorapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.jason.visitorapp.Adapters.VisitorsListAdapter;
import com.example.jason.visitorapp.Helpers.SQliteHelper;
import com.example.jason.visitorapp.modals.Visitors;
import com.example.jason.visitorapp.modals.visitorsModel;

import java.util.ArrayList;

public class Vistors extends AppCompatActivity {
RecyclerView visitorsList;
VisitorsListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistors);
        visitorsList = findViewById(R.id.visitorsList);
        //visitorsModel.getVisitorsModel(getApplicationContext()).getAllVistiors();
        adapter = new VisitorsListAdapter(getApplicationContext());
        visitorsList.setLayoutManager(new LinearLayoutManager(this));
        visitorsList.setAdapter(adapter);
        //updatelist();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id ==R.id.main_menu){{
            Intent in = new Intent(Vistors.this,SigninForm.class);
            startActivity(in);
        }

        }
        return super.onOptionsItemSelected(item);
    }

    public void  updatelist(){
        visitorsModel.getVisitorsModel(getApplicationContext()).removeVistors();
        //visitorsModel.getVisitorsModel(getApplicationContext()).getAllVistiors();
    }


}
