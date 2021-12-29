package com.smi.test.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.smi.test.R;

import java.io.InputStream;
import java.net.URL;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "EmailPasswordActivity";
    private EditText mEdtEmail, mEdtPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ImageView mImageView;
    private TextView mTextViewProfile;
    private TextInputLayout mLayoutEmail, mLayoutPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTextViewProfile = findViewById(R.id.profile);
        mEdtEmail = findViewById(R.id.edt_email);
        mEdtPassword = findViewById(R.id.edt_password);
        mImageView = findViewById(R.id.logo);
        mLayoutEmail = findViewById(R.id.layout_email);
        mLayoutPassword = findViewById(R.id.layout_password);

        findViewById(R.id.email_sign_in_button).setOnClickListener(this);
        findViewById(R.id.email_create_account_button).setOnClickListener(this);

        // findViewById(R.id.verify_button).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                //updateUI(user);
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void signIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                if (!task.isSuccessful()) {
                    mTextViewProfile.setTextColor(Color.RED);
                    mTextViewProfile.setText(task.getException().getMessage());
                } else {
                    mTextViewProfile.setText("Connecté avec succés");
                    mTextViewProfile.setTextColor(Color.DKGRAY);

                    Intent intent = new Intent(LoginActivity.this, BrandActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    mTextViewProfile.setTextColor(Color.RED);
                    mTextViewProfile.setText(task.getException().getMessage());
                } else {
                    mTextViewProfile.setTextColor(Color.DKGRAY);
                }

            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.email_create_account_button:
                createAccount(mEdtEmail.getText().toString(), mEdtPassword.getText().toString());
                break;
            case R.id.email_sign_in_button:
                signIn(mEdtEmail.getText().toString(), mEdtPassword.getText().toString());

                break;
        }
    }
}

