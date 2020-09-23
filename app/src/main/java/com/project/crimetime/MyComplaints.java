package com.project.crimetime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

//import com.project.crimetime.Adapters.ComplainRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.project.crimetime.Classes.ComplainClass;

import java.util.ArrayList;

public class MyComplaints extends AppCompatActivity {

    RecyclerView recycler;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    String userId;
    CollectionReference collectionReference;
    userComplainAdapter userComplainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_complains);
        recycler = findViewById(R.id.complain_recycler);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userId=firebaseAuth.getCurrentUser().getUid();

        setRecyclerView();
    }

    private void setRecyclerView() {
        collectionReference=firebaseFirestore.collection("complaints").document(userId).collection("complaint details");
        Query query=collectionReference.orderBy("date", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<ComplainClass> options=new FirestoreRecyclerOptions.Builder<ComplainClass>()
                .setQuery(query,ComplainClass.class)
                .build();

        userComplainAdapter=new userComplainAdapter(options);

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(userComplainAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        userComplainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        userComplainAdapter.stopListening();
    }
}