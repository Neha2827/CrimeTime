package com.project.crimetime.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.crimetime.LogIn;
import com.project.crimetime.R;

import java.util.HashMap;
import java.util.Map;

public class AdminRegister extends Fragment {
    EditText mfullName,mEmail,mPoliceid,mpincode;
    Button register;
    FirebaseAuth fAuth;
    String userId;
    FirebaseFirestore fStore;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //Button register;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_admin_register, container, false);


        register = v.findViewById(R.id.admin_register);
        mfullName = v.findViewById(R.id.user_reg);
        mEmail = v.findViewById(R.id.email_reg);
        mPoliceid = v.findViewById(R.id.police_id);
        mpincode = v.findViewById(R.id.police_station);
        fAuth = FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = mEmail.getText().toString().trim();
                final String policeid = mPoliceid.getText().toString().trim();
                final String pincode = mpincode.getText().toString().trim();
                final String fullname=mfullName.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required.");
                    return;
                } else if (TextUtils.isEmpty(policeid)) {
                    mPoliceid.setError("Police_id is Required.");
                    return;
                }
                     else if  (TextUtils.isEmpty(policeid)) {
                        mPoliceid.setError("Password is Required.");
                        return;
                    }
                else if (TextUtils.isEmpty(pincode)) {
                    mpincode.setError("pincode is required.");
                    return;
                } else {

                    //register user in firebase
                    fAuth.createUserWithEmailAndPassword(email, policeid).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(getActivity(), "requested for admin.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getContext(), LogIn.class);
                                startActivity(intent);
                                userId = fAuth.getCurrentUser().getUid();
                                DocumentReference documentReference=fStore.collection("users").document(userId);

                                Map<String,Object> user=new HashMap<>();
                                user.put("Name",fullname);
                                user.put("Email",email);
                                user.put("Policeid",policeid);
                                user.put("Pincode",pincode);

                                documentReference.set(user);



                            } else {
                                Toast.makeText(getActivity(), "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }

            }
        });
        return v;
    }
}