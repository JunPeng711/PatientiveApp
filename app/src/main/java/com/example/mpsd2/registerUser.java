package com.example.mpsd2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class registerUser extends AppCompatActivity implements View.OnClickListener {



    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private TextView login;
    private ImageButton registerButton;
    private EditText registerEmail, registerName, registerAge, registerPhone, registerPassword;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        login = (TextView) findViewById(R.id.loginUser);
        login.setOnClickListener(this);

        registerButton = (ImageButton) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        registerEmail = (EditText) findViewById(R.id.registerEmail);
        registerName = (EditText) findViewById(R.id.registerName);
        registerAge = (EditText) findViewById(R.id.registerAge);
        registerPhone = (EditText) findViewById(R.id.registerPhone);
        registerPassword = (EditText) findViewById(R.id.registerPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginUser:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registerButton:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = registerEmail.getText().toString().trim();
        String name = registerName.getText().toString().trim();
        String age = registerAge.getText().toString().trim();
        String phone = registerPhone.getText().toString().trim();
        String password = registerPassword.getText().toString().trim();

        if(email.isEmpty()){
            registerEmail.setError("Email Required!");
            registerEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            registerEmail.setError("Please provide valid email!");
            registerEmail.requestFocus();
            return;
        }
        if(name.isEmpty()){
            registerName.setError("Name Required!");
            registerName.requestFocus();
            return;
        }
        if(age.isEmpty()){
            registerAge.setError("Age Required!");
            registerAge.requestFocus();
            return;
        }
        if(age.length()>2){
            registerAge.setError("Please enter a valid Age!");
            registerAge.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            registerPhone.setError("Phone Number Required!");
            registerPhone.requestFocus();
            return;
        }
        if(!Patterns.PHONE.matcher(phone).matches()){
            registerPhone.setError("Please enter a valid phone!");
            registerPhone.requestFocus();
            return;
        }
        if(password.isEmpty()){
            registerPassword.setError("Password Required!");
            registerPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            registerPassword.setError("Password length should be more than 6 characters");
            registerPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    User user = new User(email,name,age,phone);

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(mAuth.getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(registerUser.this, "Register Successful!", Toast.LENGTH_LONG).show();

                                        //redirect to login layout!
                                    }else{
                                        Toast.makeText(registerUser.this, "Failed to Register. Try Again!", Toast.LENGTH_SHORT).show();
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }
                            });

                        }else{
                            Toast.makeText(registerUser.this, "Failed to Register. Try Again!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}