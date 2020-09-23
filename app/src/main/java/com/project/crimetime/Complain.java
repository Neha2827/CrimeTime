package com.project.crimetime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.crimetime.Classes.ComplainClass;

public class Complain extends AppCompatActivity {
    Button launch;
    EditText mEtName,mEtAddress,mEtPin,mEtDate,mEtContact,mEtComplaint;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

   // Button launch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);
        launch = findViewById(R.id.launch_btn);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        mEtAddress=findViewById(R.id.complainer_address);
        mEtComplaint=findViewById(R.id.complain_detail);
        mEtContact=findViewById(R.id.phone);
        mEtDate=findViewById(R.id.date);
        mEtName=findViewById(R.id.missing_name);
        mEtPin=findViewById(R.id.pincode);

        launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchComplain();
            }
        });
    }

    public void launchComplain(){
        //STORING DATA
        String id=firebaseAuth.getCurrentUser().getUid();
        CollectionReference collectionReference=firebaseFirestore.collection("complaints").document(id).collection("complaint details");
        final String name=mEtName.getText().toString();
        String address=mEtAddress.getText().toString();
        String pin=mEtPin.getText().toString();
        String complain=mEtComplaint.getText().toString();
        String date=mEtDate.getText().toString();
        String contact=mEtContact.getText().toString();
        String status="complaint sent";

        ComplainClass complainClass=new ComplainClass(name,date,complain,status,pin,contact,address);
        collectionReference.add(complainClass).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                Toast.makeText(getApplicationContext(),"Check MY COMPLAIN section to view your complaint status",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),HomeScreen.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Error While Saving your Data",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),HomeScreen.class));
            }
        });


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