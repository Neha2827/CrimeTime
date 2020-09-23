package com.project.crimetime;

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
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MissingComplain extends AppCompatActivity {
    ImageView imageView;
    EditText mEtmissing_name,mEtage,mEtheight,mEtmissing_skin,mEthair,mEtmissing_time,mEtmissing_place;
    EditText mEtcomplainer_name,mEtphone,mEtaddress,mEtpincode,mEtdate;
    Button launch;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    String id;
    CollectionReference collectionReference=firebaseFirestore.collection("missing complaints").
            document(id).collection("missing complaint details");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_complain);
        imageView=findViewById(R.id.img_view);
        mEtmissing_name=findViewById(R.id.missing_name);
        mEtage=findViewById(R.id.age);
        mEtheight=findViewById(R.id.height);
        mEtmissing_skin=findViewById(R.id.missing_skin);
        mEthair=findViewById(R.id.hair);
        mEtmissing_place=findViewById(R.id.missing_place);
        mEtmissing_time=findViewById(R.id.missing_time);
        mEtcomplainer_name=findViewById(R.id.complainer_name);
        mEtphone=findViewById(R.id.complainer_phone);
        mEtaddress=findViewById(R.id.complainer_address);
        mEtpincode=findViewById(R.id.pincode);
        mEtdate=findViewById(R.id.date);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        launch = findViewById(R.id.launch_btn);




        launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // dialog box
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