package com.project.crimetime.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.project.crimetime.AdminHome;
import com.project.crimetime.HomeScreen;
import com.project.crimetime.R;

public class AdminLogin extends Fragment {
    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;
    public static final String KEY_IS_ADMIN_LOGGED_IN = "ISADMINLOGGEDIN";
    public static final String KEY_PASSWORD = "PASSWORD";
    public static final String KEY_EMAIL_ADDRESS = "EMAILADDRESS";

    EditText mEmail, mPassword;
    Button signIn;
    TextView forgotpassword;
    FirebaseAuth fAuth;
    CheckBox mshowpass, mChkRememberMe;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //Button signIn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_admin_login, container, false);
        signIn = v.findViewById(R.id.Admin_login);
        mEmail = v.findViewById(R.id.log_admin_id);
        mPassword = v.findViewById(R.id.log_password);
        mshowpass = v.findViewById(R.id.showpass);
        forgotpassword = v.findViewById(R.id.forgot);
        fAuth = FirebaseAuth.getInstance();
        mChkRememberMe=v.findViewById(R.id.chk_remember);
        prefManager = this.getActivity().getSharedPreferences("APP",0);
        editor = prefManager.edit();
        boolean isAdminAlreadyLoggedIn = prefManager.getBoolean("ISADMINLOGGEDIN", false);
        String emailAddress = prefManager.getString(KEY_EMAIL_ADDRESS, "");
        mEmail.setText(emailAddress);
        if (isAdminAlreadyLoggedIn) {
            Intent intent = new Intent(getContext(), AdminHome.class);
            startActivity(intent);
        }


        mshowpass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    mPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                }
            }
        });


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required.");
                    return;
                }


                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required.");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password Must be >=6 Character");
                    return;
                }
                editor.putString(KEY_EMAIL_ADDRESS, email);
                editor.putString(KEY_PASSWORD, password);
                boolean isRememberMe = mChkRememberMe.isChecked();
                if (isRememberMe) {
                    editor.putBoolean(KEY_IS_ADMIN_LOGGED_IN, true);
                }
                editor.apply();
                //authenticate the user
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "logged in.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), AdminHome.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


               // Intent intent = new Intent(getContext(), AdminHome.class);
                //startActivity(intent);
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // extract the email and send reset link

                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity(), "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });
                passwordResetDialog.create().show();


            }
        });
        return v;

    }
}


