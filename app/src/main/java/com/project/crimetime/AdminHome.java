package com.project.crimetime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.crimetime.Fragments.AdminLogin;
import com.project.crimetime.Fragments.UserLogin;

public class AdminHome extends AppCompatActivity {

    Button complain;
    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        complain = findViewById(R.id.admin_complain);
        prefManager = getSharedPreferences("APP", 0);
        editor = prefManager.edit();
        string=firebaseAuth.getCurrentUser().getUid();


        complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              firebaseFirestore.collection("admin").document(string).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                  @Override
                  public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                      if (task.getResult().exists()){
                          Intent intent = new Intent(AdminHome.this, AdminComplaints.class);
                          startActivity(intent);
                      }
                      else {
                          Toast.makeText(AdminHome.this, "Not Registered as Admin", Toast.LENGTH_LONG).show();

                          Intent back=new Intent(AdminHome.this,LogIn.class);
                          startActivity(back);
                          finish();
                      }

                  }
              });
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