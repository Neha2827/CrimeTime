package com.project.crimetime.Fragments;

import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.project.crimetime.LogIn;
import com.project.crimetime.R;
import com.project.crimetime.Verhoeff;


public class UserRegister extends Fragment {
    EditText mFullName, mAdhar, mEmail, mPassword, mconfirmPassword;
    Button register;
    FirebaseAuth fAuth;




    public UserRegister() {

    }


    //Button register;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_register, container, false);

        mFullName = v.findViewById(R.id.user_reg);
        mAdhar = v.findViewById(R.id.adhar);
        mEmail = v.findViewById(R.id.email_reg);
        mPassword = v.findViewById(R.id.password_reg);
        mconfirmPassword = v.findViewById(R.id.confirm_pass);
        register = v.findViewById(R.id.user_register);


        fAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String confirmpassword = mconfirmPassword.getText().toString().trim();
                String adhar = mAdhar.getText().toString().trim();

                boolean result = Verhoeff.validateVerhoeff(adhar);
                String msg = String.valueOf(result);

                if (msg == "true") {
                    Toast.makeText(getActivity(), "valid aadhar_number", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "invalid_aadhar_number", Toast.LENGTH_LONG).show();
                }


                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required.");
                    return;
                }


                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required.");
                    return;
                }

                if (TextUtils.isEmpty(confirmpassword)) {
                    mconfirmPassword.setError("reconfirmation required.");
                    return;
                }
                if (TextUtils.isEmpty(adhar)) {
                    mAdhar.setError("Aadhar_number is necessary.");
                    return;
                }


                if (password.length() < 6) {
                    mPassword.setError("Password Must be >=6 Character");
                    return;
                }
                if (!password.equals(confirmpassword)) {
                    Toast.makeText(getActivity(), "your password do not match with your confirm password", Toast.LENGTH_LONG).show();
                }else {


                    //register user in firebase
                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "user created.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getContext(), LogIn.class);
                                startActivity(intent);


                            } else {
                                Toast.makeText(getActivity(), "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }

                Intent intent = new Intent(getContext(), LogIn.class);
                startActivity(intent);

            }
        });
        return v;
    }
}