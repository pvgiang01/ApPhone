package com.ssv.appsalephone.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ssv.appsalephone.Home;
import com.ssv.appsalephone.R;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    TextView tvRegister,tvForgot;
    EditText edtEmail,edtPass;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_signup);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass =findViewById(R.id.edtPass);
        tvForgot = findViewById(R.id.tv_forgotPass);
        mAuth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void loginUser(){
        String email = edtEmail.getText().toString();
        String pass = edtPass.getText().toString();
        if(TextUtils.isEmpty(email)){
            edtEmail.setError("Email cannot be empty");
            edtEmail.requestFocus();
        }else if(TextUtils.isEmpty(pass)){
            edtPass.setError("Password cannot be empty");
            edtPass.requestFocus();
        }else {
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"Login successfully",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, Home.class));
                    }else {
                        Toast.makeText(LoginActivity.this,"Login Error: " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}