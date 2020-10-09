package com.project.crimetime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.project.crimetime.Classes.MissingcomplainClass;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.UUID;

public class MissingComplain extends AppCompatActivity {
    int complainno=0;
    MissingcomplainClass missingcomplainClass;
    ImageView imageView;
    EditText mEtmissing_name,mEtage,mEtheight,mEtmissing_skin,mEthair,mEtmissing_time,mEtmissing_place;
    EditText mEtcomplainer_name,mEtphone,mEtaddress,mEtpincode,mEtdate;
    Button launch;
    private StorageReference storageReference;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    private Uri imageuri;
    String id;
   CollectionReference collectionReference;

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
        storageReference = FirebaseStorage.getInstance().getReference();
        id=firebaseAuth.getCurrentUser().getUid();
        launch = findViewById(R.id.launch_btn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(MissingComplain.this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                            PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MissingComplain.this, "DENIED", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(MissingComplain.this, new String[]
                                        {Manifest.permission.READ_EXTERNAL_STORAGE},
                                1);


                    } else {
                        ChoseImage();
                    }
                } else {
                    ChoseImage();
                }
            }
        });
        CollectionReference LastcollectionReference=firebaseFirestore.collection("Missing complaints").
                document(id).collection("Missing complaint details");
        Query lastquery=LastcollectionReference.orderBy("date", Query.Direction.DESCENDING).limit(1);
        lastquery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot queryDocumentSnapshot:task.getResult()){
                        missingcomplainClass= (MissingcomplainClass) queryDocumentSnapshot.toObject(MissingcomplainClass.class);
                    }

                }

            }
        });
       /* lastquery.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
           // missingcomplainClass= (MissingcomplainClass) queryDocumentSnapshots.toObjects(MissingcomplainClass.class);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });*/





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
        //storing image and data


        File newfile=new File(imageuri.getPath());
        final String missingname=mEtmissing_name.getText().toString();
        final String missingage=mEtage.getText().toString();
        final String missingheight=mEtheight.getText().toString();
        final String missingskin=mEtmissing_skin.getText().toString();
        final String missinghair=mEthair.getText().toString();
        final String missingtime=mEtmissing_time.getText().toString();
        final String missingplace=mEtmissing_place.getText().toString();
        final String complainername=mEtcomplainer_name.getText().toString();
        final String complainerphone=mEtphone.getText().toString();
        final String complaineraddress=mEtaddress.getText().toString();
        final String complainerpin=mEtpincode.getText().toString();
        final String complaintdate=mEtdate.getText().toString();

        if(missingcomplainClass!=null&&missingcomplainClass.getComplainNo()!=null){
            complainno=Integer.parseInt(missingcomplainClass.getComplainNo());


        }
        complainno++;
        final String status="Complaint sent";
        final StorageReference imageref=storageReference.child("user_image/"+ UUID.randomUUID().toString()+".jpg");
        imageref.putFile(Uri.fromFile(newfile)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        storeuserdata(missingname,missingage,missingheight,missingskin,missinghair,missingtime,missingplace
                        ,complainername,complaineraddress,complainerphone,complainerpin,complaintdate,uri.toString(),status,
                                String.valueOf(complainno));


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MissingComplain.this,"failure",Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MissingComplain.this,"Failed",Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void storeuserdata(String missingname, String missingage, String missingheight,
                               String missingskin, String missinghair, String missingtime, String missingplace,
                               String complainername, String complaineraddress, String complainerphone, String complainerpin,
                               String complaintdate, String image, String status, String complainno) {
        MissingcomplainClass missingcomplainClass=new MissingcomplainClass( missingname,  missingage,
                missingheight,
                missingskin,  missinghair,  missingtime,  missingplace,
                complainername, complaineraddress,  complainerphone,  complainerpin,
                complaintdate, image,status,complainno);
        collectionReference=firebaseFirestore.collection("missing complaints").
                document(id).collection("missing complaint details");
        collectionReference.add(missingcomplainClass).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                Toast.makeText(getApplicationContext(),"Check MY COMPLAIN section to view your complaint status",
                        Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Error While Saving your Data",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),HomeScreen.class));

            }
        });



    }

    private void ChoseImage(){
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1,1).start(MissingComplain.this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result=CropImage.getActivityResult(data);
            if(resultCode==RESULT_OK){
                imageuri=result.getUri();
                imageView.setImageURI(imageuri);
            }else if (resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error=result.getError();
            }
        }
    }


}