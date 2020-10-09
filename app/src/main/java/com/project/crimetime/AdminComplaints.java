package com.project.crimetime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.crimetime.Classes.AdminViewComplain;
import com.project.crimetime.Classes.ComplainClass;
import com.project.crimetime.Classes.DocumentsId;

import java.util.ArrayList;

public class AdminComplaints extends AppCompatActivity {

    TextView choose_down, choose_up, complaints, missingComplaints;
    LinearLayout extra;
    EditText pin;
    Button search;
    RecyclerView mRcAdmin;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    //AdminComplainAdapter adminComplainAdapter;
    CollectionReference collectionReference;
    ArrayList<String> arrayList;
    RecyclerView.LayoutManager layoutManager;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_complaints);

        choose_down = findViewById(R.id.choose_down);
        choose_up = findViewById(R.id.choose_up);
        pin = findViewById(R.id.pin);
        search = findViewById(R.id.search);
        mRcAdmin = findViewById(R.id.rv_admin);
        extra = findViewById(R.id.extra);
        complaints = findViewById(R.id.complaints);
        missingComplaints = findViewById(R.id.missing_complaints);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        arrayList = new ArrayList<String>();
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRcAdmin.setLayoutManager(layoutManager);


        if (choose_up.getVisibility() == View.GONE) {
            choose_down.setVisibility(View.VISIBLE);
        }

        choose_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extra.setVisibility(View.VISIBLE);
                choose_up.setVisibility(View.VISIBLE);
                choose_down.setVisibility(View.GONE);
            }
        });

        choose_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extra.setVisibility(View.GONE);
                choose_up.setVisibility(View.GONE);
                choose_down.setVisibility(View.VISIBLE);

            }
        });

        complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose_down.setText("Complaints");
                choose_down.setVisibility(View.VISIBLE);
                choose_up.setVisibility(View.GONE);
                extra.setVisibility(View.GONE);
                setRecyclerview();

            }
        });

        missingComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose_down.setText("Missing Complaints");
                choose_down.setVisibility(View.VISIBLE);
                choose_up.setVisibility(View.GONE);
                extra.setVisibility(View.GONE);

            }
        });

    }

    public void setRecyclerview() {
        mRcAdmin.setVisibility(View.VISIBLE);
        CollectionReference collectionReference = firebaseFirestore.collection("complaints");
        //Query query=collectionReference.sorBy( Query.Direction.DESCENDING);
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot:task.getResult()){
                        String id=documentSnapshot.getId();
                        arrayList.add(id);
                    }
                    adapter=new MainAdapter(arrayList);
                    mRcAdmin.setAdapter(adapter);
                }

            }
        });




    }
}

/*      DocumentsId documentsId=documentSnapshot.toObject(DocumentsId.class);
                    documentsId.setDoc(documentSnapshot.getId());
                    String docId=documentsId.getDoc();

                    arrayList.add(docId);*/

