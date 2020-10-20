package com.project.crimetime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.crimetime.Fragments.UserLogin;

public class HomeScreen extends AppCompatActivity {

    Button launchComplain, myComplaints,mymissingComplaints,crimeReport;
    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mymissingComplaints=findViewById(R.id.missing_complain);

        launchComplain = findViewById(R.id.launch_complain);
        myComplaints = findViewById(R.id.mycomplain);
        crimeReport = findViewById(R.id.report);
        prefManager = getSharedPreferences("APP", 0);
        editor = prefManager.edit();


       // String userName = prefManager.getString(UserLogin.KEY_EMAIL_ADDRESS, "No Value available");

        mymissingComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeScreen.this,MyMissing.class);
                startActivity(intent);
            }
        });

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
    public void onLogoutClicked(View view) {
       editor.putBoolean(UserLogin.KEY_IS_USER_LOGGED_IN, false);
       editor.putString(UserLogin.KEY_PASSWORD, "");
       editor.apply();
       Intent intent=new Intent(HomeScreen.this,LogIn.class);
       intent.putExtra("USER_LOGIN",true);
       intent.putExtra("LOGOUT",true);
        startActivity(intent);
        finish();
    }
}