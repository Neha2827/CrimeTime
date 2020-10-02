package com.project.crimetime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.project.crimetime.Classes.ComplainClass;
import com.project.crimetime.Classes.MissingcomplainClass;

public class MyMissing extends AppCompatActivity {
    RecyclerView recycler;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    String userId;
    CollectionReference collectionReference;
    userComplainAdapter userComplainAdapter;
    userMissingComplainAdapter userMissingComplainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_missing);
        recycler = findViewById(R.id.missing_complain_recycler);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userId=firebaseAuth.getCurrentUser().getUid();

        setRecyclerView();
    }
    private void setRecyclerView() {
        collectionReference=firebaseFirestore.collection("missing complaints").document(userId).
                collection("missing complaint details");
        Query query=collectionReference.orderBy("date", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<MissingcomplainClass> options=new FirestoreRecyclerOptions.Builder<MissingcomplainClass>()
                .setQuery(query,MissingcomplainClass.class)
                .build();

        userMissingComplainAdapter=new userMissingComplainAdapter(options);

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(userComplainAdapter);
    }
}