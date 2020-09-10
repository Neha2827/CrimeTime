package com.project.crimetime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHome extends AppCompatActivity {

    Button complain, adminRequest, processing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        complain = findViewById(R.id.admin_complain);
        adminRequest = findViewById(R.id.admin_req);
        processing = findViewById(R.id.processing);

        complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHome.this, AdminComplaints.class);
                startActivity(intent);
            }
        });

        adminRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHome.this, AdminRequest.class);
                startActivity(intent);
            }
        });

        processing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHome.this, AdminProcessing.class);
                startActivity(intent);
            }
        });
    }
}