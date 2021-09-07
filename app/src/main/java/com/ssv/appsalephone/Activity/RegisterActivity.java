package com.ssv.appsalephone.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ssv.appsalephone.R;

public class RegisterActivity extends AppCompatActivity {
    Button btnRegister;
    TextView tvSignIn;
    EditText edt_email,edt_pass;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btn_register);
        tvSignIn = findViewById(R.id.tv_signin);
        edt_email = findViewById(R.id.et_email);
        edt_pass = findViewById(R.id.et_pass);
        mAuth = FirebaseAuth.getInstance();
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }
    private void createUser(){
        String email = edt_email.getText().toString();
        String pass = edt_pass.getText().toString();
        if(TextUtils.isEmpty(email)){
            edt_email.setError("Email cannot be empty");
            edt_email.requestFocus();
        }else if(TextUtils.isEmpty(pass)){
            edt_pass.setError("Password cannot be empty");
            edt_pass.requestFocus();
        }else {
            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"User Registered successfully",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    }else {
                        Toast.makeText(RegisterActivity.this,"Registered Error: " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
    }