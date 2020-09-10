package com.project.crimetime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class Complain extends AppCompatActivity {

    Button launch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);
        launch = findViewById(R.id.launch_btn);

        launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchComplain();
            }
        });
    }

    public void launchComplain(){
        LayoutInflater inflater =LayoutInflater.from(this);
        View prompt =inflater.inflate(getResources().getLayout(R.layout.confirm_dialog), null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(prompt);
        final AlertDialog dialog1 =builder.create();
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button ok;
        ok = prompt.findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }

        });

        try {
            dialog1.show();
        }catch (Exception e){
            Log.d("exception", e.getMessage());
        }
    }
}