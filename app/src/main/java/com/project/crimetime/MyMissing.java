package com.project.crimetime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.crimetime.Classes.ComplainClass;
import com.project.crimetime.Classes.MissingcomplainClass;

import java.util.ArrayList;

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
       // Query query=collectionReference.orderBy("date", Query.Direction.DESCENDING);

      //  FirestoreRecyclerOptions<MissingcomplainClass> options=new FirestoreRecyclerOptions.Builder<MissingcomplainClass>()
              //  .setQuery(query,MissingcomplainClass.class)
               // .build();

        final ArrayList<MissingcomplainClass> userMissingComplaints = new ArrayList<>();

        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot snapshot  : task.getResult()){
                        MissingcomplainClass complaint = snapshot.toObject(MissingcomplainClass.class);
                        userMissingComplaints.add(complaint);
                    }
                    userMissingComplainAdapter adapter = new userMissingComplainAdapter(MyMissing.this, userMissingComplaints);
                    recycler.setAdapter(adapter);
                }
            }
        });

//        userMissingComplainAdapter=new userMissingComplainAdapter(options);


    }
}