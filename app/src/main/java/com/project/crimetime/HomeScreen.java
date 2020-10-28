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
import com.project.crimetime.Fragments.UserLogin;

public class HomeScreen extends AppCompatActivity {

    Button launchComplain, myComplaints,mymissingComplaints,crimeReport;
    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    String string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        mymissingComplaints=findViewById(R.id.missing_complain);

        launchComplain = findViewById(R.id.launch_complain);
        myComplaints = findViewById(R.id.mycomplain);
        crimeReport = findViewById(R.id.report);
        prefManager = getSharedPreferences("APP", 0);
        editor = prefManager.edit();
        string=firebaseAuth.getCurrentUser().getUid();


       // String userName = prefManager.getString(UserLogin.KEY_EMAIL_ADDRESS, "No Value available");

        mymissingComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               firebaseFirestore.collection("users").document(string).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                       if (task.getResult().exists()){
                           Intent intent=new Intent(HomeScreen.this,MyMissing.class);
                           startActivity(intent);
                       }else {
                           Toast.makeText(HomeScreen.this, "Not Registered as User", Toast.LENGTH_LONG).show();
                           Intent back=new Intent(HomeScreen.this,LogIn.class);
                           startActivity(back);
                           finish();

                       }

                   }
               });
            }
        });

        launchComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              firebaseFirestore.collection("users").document(string).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                  @Override
                  public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                      if (task.getResult().exists()){
                          Intent intent = new Intent(HomeScreen.this,LaunchComplain.class);
                          startActivity(intent);
                      }
                      else {
                          Toast.makeText(HomeScreen.this, "Not Registered as User", Toast.LENGTH_LONG).show();

                          Intent back=new Intent(HomeScreen.this,LogIn.class);
                          startActivity(back);
                          finish();


                      }

                  }
              });
            }
        });

        myComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               firebaseFirestore.collection("users").document(string).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                       if (task.getResult().exists()){
                           Intent intent = new Intent(HomeScreen.this,MyComplaints.class);
                           startActivity(intent);
                       }else {
                           Toast.makeText(HomeScreen.this, "Not Registered as User", Toast.LENGTH_LONG).show();

                           Intent back=new Intent(HomeScreen.this,LogIn.class);
                           startActivity(back);
                           finish();
                       }

                   }
               });
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