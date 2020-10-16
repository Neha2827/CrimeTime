package com.project.crimetime.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.project.crimetime.HomeScreen;
import com.project.crimetime.R;

import io.grpc.Context;


public class UserLogin
        extends Fragment {

    public static final int GOOGLE_SIGN_IN_CODE = 10005;

    EditText mEmail, mPassword;
    Button signIn;
    TextView mlogin, forgotpassword;
    FirebaseAuth fAuth;
    CheckBox mshowpass;
    private CheckBox mChkRememberMe;
    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;
    public static final String KEY_IS_USER_LOGGED_IN = "ISUSERLOGGEDIN";
    public static final String KEY_PASSWORD = "PASSWORD";
    public static final String KEY_EMAIL_ADDRESS = "EMAILADDRESS";
    FirebaseAuth mAuth;
    SignInButton signInButton;
    GoogleSignInClient signInClient;
    GoogleSignInOptions gso;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_login, container, false);

        signIn = v.findViewById(R.id.user_login);
        mEmail = v.findViewById(R.id.log_email);
        mPassword = v.findViewById(R.id.log_password);
        mshowpass = v.findViewById(R.id.showpass);
        fAuth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        forgotpassword = v.findViewById(R.id.forgot);
        signInButton = v.findViewById(R.id.log_googlesign);
        mChkRememberMe=v.findViewById(R.id.chk_remember);
        prefManager = this.getActivity().getSharedPreferences("PREF",0);
        editor = prefManager.edit();
        boolean isUserAlreadyLoggedIn = prefManager.getBoolean("ISUSERLOGGEDIN", false);

        String emailAddress = prefManager.getString(KEY_EMAIL_ADDRESS, "");

        mEmail.setText(emailAddress);
        if (isUserAlreadyLoggedIn) {
            Intent intent = new Intent(getContext(), HomeScreen.class);
            startActivity(intent);
        }


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("937598126270-6um1v19e4f2jpdj1552t92k4cfn6ebsp.apps.googleusercontent.com")
                .requestEmail()
                .build();
        signInClient = GoogleSignIn.getClient(getActivity(), gso);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (signInAccount != null) {
            startActivity(new Intent(getActivity(), HomeScreen.class));
            //Toast.makeText(getActivity(), "User logged in Already", Toast.LENGTH_SHORT).show();
        }
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign = signInClient.getSignInIntent();
                startActivityForResult(sign, GOOGLE_SIGN_IN_CODE);
            }
        });


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

                boolean isRememberMe = mChkRememberMe.isChecked();

                editor.putString(KEY_EMAIL_ADDRESS, email);
                editor.putString(KEY_PASSWORD, password);
                if (isRememberMe) {
                    editor.putBoolean(KEY_IS_USER_LOGGED_IN, true);
                }
                editor.apply();



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

                //authenticate the user
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "logged in.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), HomeScreen.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


                //Intent intent = new Intent(getContext(), HomeScreen.class);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN_CODE) {
            Task<GoogleSignInAccount> signInTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount signInAcc = signInTask.getResult(ApiException.class);

                AuthCredential authCredential = GoogleAuthProvider.getCredential(signInAcc.getIdToken(), null);
                mAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(getActivity(), "your google account is connected to our application", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), HomeScreen.class));
                    }
                });


            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }
}











