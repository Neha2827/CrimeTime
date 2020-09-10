package com.project.crimetime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    Button launchComplain, myComplaints, crimeReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        launchComplain = findViewById(R.id.launch_complain);
        myComplaints = findViewById(R.id.mycomplain);
        crimeReport = findViewById(R.id.report);

        launchComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,LaunchComplain.class);
                startActivity(intent);
            }
        });

        myComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,MyComplaints.class);
                startActivity(intent);
            }
        });

        crimeReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,CrimeRateReport.class);
                startActivity(intent);
            }
        });
    }
}