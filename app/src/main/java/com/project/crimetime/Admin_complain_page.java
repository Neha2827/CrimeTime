package com.project.crimetime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.project.crimetime.Classes.ComplainClass;

public class Admin_complain_page extends AppCompatActivity {

    TextView mTvHead;
    String ReqId;
    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    AdminComplainAdapter adminComplainAdapter;
    CollectionReference collectionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_complain_page);
        mTvHead=findViewById(R.id.tv_head);
        recyclerView=findViewById(R.id.rv_complain_Page);
        firebaseFirestore=FirebaseFirestore.getInstance();
        Bundle bundle=getIntent().getExtras();
        ReqId=bundle.getString("id");

        setData();

    }

    public void setData(){
        collectionReference=firebaseFirestore.collection("complaints").document(ReqId).collection("complaint details");
        Query query=collectionReference.orderBy("date", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<ComplainClass> options=new FirestoreRecyclerOptions.Builder<ComplainClass>()
                .setQuery(query,ComplainClass.class)
                .build();

        adminComplainAdapter=new AdminComplainAdapter(options);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adminComplainAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        adminComplainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adminComplainAdapter.stopListening();
    }
}