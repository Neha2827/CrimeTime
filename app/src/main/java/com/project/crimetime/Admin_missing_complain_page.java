package com.project.crimetime;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.crimetime.Classes.MissingcomplainClass;
import java.util.ArrayList;
public class Admin_missing_complain_page extends AppCompatActivity {
    TextView mTvHead;
    String ReqId;
    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    CollectionReference collectionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_missing_complain_page);
        mTvHead=findViewById(R.id.tv_head);
        recyclerView=findViewById(R.id.rv_missing_complain_Page);
        firebaseFirestore=FirebaseFirestore.getInstance();
        Bundle bundle=getIntent().getExtras();
        ReqId=bundle.getString("id1");
        setData();
    }
    public void setData(){
        collectionReference=firebaseFirestore.collection("missing complaints").document(ReqId).
                    collection("missing complaint details");

            final ArrayList<MissingcomplainClass> userMissingComplaints = new ArrayList<>();

            collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot snapshot  : task.getResult()){
                            MissingcomplainClass complaint = snapshot.toObject(MissingcomplainClass.class);
                            userMissingComplaints.add(complaint);
                        }
                        AdminMissingAdapter adapter = new AdminMissingAdapter(Admin_missing_complain_page.this, userMissingComplaints);
                        recyclerView.setAdapter(adapter);
                    }
                }
            });
    }


}