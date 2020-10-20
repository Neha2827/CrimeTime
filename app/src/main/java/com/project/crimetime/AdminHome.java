package com.project.crimetime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.crimetime.Fragments.AdminLogin;
import com.project.crimetime.Fragments.UserLogin;

public class AdminHome extends AppCompatActivity {

    Button complain;
    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        complain = findViewById(R.id.admin_complain);
        prefManager = getSharedPreferences("APP", 0);
        editor = prefManager.edit();


        complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHome.this, AdminComplaints.class);
                startActivity(intent);
            }
        });
    }

        public void onLogoutClicked(View view) {
            editor.putBoolean(AdminLogin.KEY_IS_ADMIN_LOGGED_IN, false);
            editor.putString(AdminLogin.KEY_PASSWORD, "");
            editor.apply();
            Intent intent=new Intent(AdminHome.this,LogIn.class);
            intent.putExtra("ADMIN_LOGIN",true);
            intent.putExtra("LOGOUT",true);
            startActivity(intent);
            finish();

        }

}