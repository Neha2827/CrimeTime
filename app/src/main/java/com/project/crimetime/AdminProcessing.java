package com.project.crimetime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.project.crimetime.Adapters.VerifyComplainRecyclerAdapter;

import java.util.ArrayList;

public class AdminProcessing extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<String> titles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_processing);

        recyclerView = findViewById(R.id.recycler_verified);
        recyclerView.setHasFixedSize(true);

        getData();
        VerifyComplainRecyclerAdapter adapter = new VerifyComplainRecyclerAdapter(titles, this);

        recyclerView.setAdapter(adapter);
    }

    public void getData(){

        titles.add("Complain 102");
        titles.add("Complain 103");
        titles.add("Complain 104");
    }
}