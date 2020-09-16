package com.project.crimetime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

//import com.project.crimetime.Adapters.ComplainRecyclerAdapter;
import com.project.crimetime.Classes.ComplainClass;

import java.util.ArrayList;

public class MyComplaints extends AppCompatActivity {

    RecyclerView recycler;
    ArrayList<ComplainClass> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_complains);

        recycler = findViewById(R.id.complain_recycler);
        recycler.setHasFixedSize(true);

  /*      list = getData();
        ComplainRecyclerAdapter adapter = new ComplainRecyclerAdapter(list, this);
        recycler.setAdapter(adapter);
    }

    public ArrayList<ComplainClass> getData(){

        list.add(new ComplainClass("12345", "Theft", "verified" ));
        list.add(new ComplainClass("12346", "murder", "verified" ));
        list.add(new ComplainClass("12347", "Theft", "Unverified" ));
        list.add(new ComplainClass("12348", "hit n run", "Unverified" ));

        return list;
    }*/
}}