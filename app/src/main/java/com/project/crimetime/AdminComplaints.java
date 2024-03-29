package com.project.crimetime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AdminComplaints extends AppCompatActivity implements MainAdapter.ClickListener {

    TextView choose_down, choose_up, complaints, missingComplaints;
    LinearLayout extra;
    RecyclerView mRcAdmin, mRcAdminMissing;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    //AdminComplainAdapter adminComplainAdapter;
    ArrayList<String> arrayList, array_missing_list;
    RecyclerView.LayoutManager layoutManager;
    MainAdapter adapter, adapter_missing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_complaints);
        choose_down = findViewById(R.id.choose_down);
        choose_up = findViewById(R.id.choose_up);
        mRcAdmin = findViewById(R.id.rv_admin_complain);
        mRcAdminMissing = findViewById(R.id.rv_admin_missing);
        extra = findViewById(R.id.extra);
        complaints = findViewById(R.id.complaints);
        missingComplaints = findViewById(R.id.missing_complaints);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        array_missing_list = new ArrayList<String>();
        arrayList = new ArrayList<String>();
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRcAdmin.setLayoutManager( new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRcAdminMissing.setLayoutManager( new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


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
                setMissing();

            }
        });

    }

    public void setRecyclerview() {
        mRcAdmin.setVisibility(View.VISIBLE);
        CollectionReference collectionReference = firebaseFirestore.collection("users");
        //Query query=collectionReference.sorBy( Query.Direction.DESCENDING);
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        String id = documentSnapshot.getId();
                        arrayList.add(id);
                    }

                    adapter = new MainAdapter(arrayList,true);
                    adapter.setListener(AdminComplaints.this);
                    mRcAdmin.setAdapter(adapter);
                }

            }

        });

    }

    public void setMissing() {
        mRcAdminMissing.setVisibility(View.VISIBLE);
        CollectionReference collectionReference = firebaseFirestore.collection("users");
        collectionReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                    String id1 = documentSnapshot.getId();
                    array_missing_list.add(id1);

                }
                adapter_missing = new MainAdapter(array_missing_list,false);
                adapter_missing.setListener(AdminComplaints.this);
                mRcAdminMissing.setAdapter(adapter_missing);
            }
        });


        //AYUSHMINA'S CODE HERE

    }


    @Override
    public void onClicked(String id) {
        Intent intent = new Intent(AdminComplaints.this, Admin_complain_page.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void onPressed(String id1) {
        Intent intent = new Intent(AdminComplaints.this, Admin_missing_complain_page.class);
        intent.putExtra("id1", id1);
        startActivity(intent);
    }
}


