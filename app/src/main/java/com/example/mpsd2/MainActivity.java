package com.example.mpsd2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView register;
    private EditText loginEmail,loginPassword;
    private Button loginButton;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.registerUser);
        register.setOnClickListener(this);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPassword = (EditText) findViewById(R.id.loginPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.registerUser:
                startActivity(new Intent(this, registerUser.class));
                break;

            case R.id.loginButton:
                userLogin();
                break;
        }
    }

    private void userLogin(){
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if(email.isEmpty()){
            loginEmail.setError("Email is Required!");
            loginEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginEmail.setError("Please enter a valid email!");
            loginEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            loginPassword.setError("Password is Required!");
            loginPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            loginPassword.setError("Password length mush be more than 6 characters!");
            loginPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    //startActivity(new Intent(getApplicationContext(), HealthEducation.class));
                    progressBar.setVisibility((View.GONE));
                }else{
                    Toast.makeText(MainActivity.this, "Failed to login. Please Check your Credential", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility((View.GONE));
                }
            }
        });

    }

}