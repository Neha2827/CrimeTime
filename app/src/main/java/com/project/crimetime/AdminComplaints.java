package com.project.crimetime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdminComplaints extends AppCompatActivity {

    TextView choose_down, choose_up, complaints, missingComplaints;
    LinearLayout extra;
    EditText pin;
    Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_complaints);

        choose_down = findViewById(R.id.choose_down);
        choose_up = findViewById(R.id.choose_up);
        pin = findViewById(R.id.pin);
        search = findViewById(R.id.search);
        extra = findViewById(R.id.extra);
        complaints = findViewById(R.id.complaints);
        missingComplaints= findViewById(R.id.missing_complaints);

        if(choose_up.getVisibility()== View.GONE){
            choose_down.setVisibility(View.VISIBLE);
        }

        choose_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extra.setVisibility(View.VISIBLE);
                choose_up.setVisibility(View.VISIBLE);
                choose_down.setVisibility(View.GONE);
            }
        });

        choose_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extra.setVisibility(View.GONE);
                choose_up.setVisibility(View.GONE);
                choose_down.setVisibility(View.VISIBLE);

            }
        });

        complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose_down.setText("Complaints");
                choose_down.setVisibility(View.VISIBLE);
                choose_up.setVisibility(View.GONE);
                extra.setVisibility(View.GONE);
            }
        });

        missingComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose_down.setText("Missing Complaints");
                choose_down.setVisibility(View.VISIBLE);
                choose_up.setVisibility(View.GONE);
                extra.setVisibility(View.GONE);
            }
        });

    }
}