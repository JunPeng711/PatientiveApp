package com.example.mpsd2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mpsd2.usermenu.ChangePhone;
import com.example.mpsd2.usermenu.ContactUs;
import com.example.mpsd2.usermenu.ForgotPassword;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity implements View.OnClickListener {

    private ImageButton changePhone,changePass,getContact,logout,home;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        final TextView userName = (TextView) findViewById(R.id.UserNameText);
        final TextView userEmail = (TextView) findViewById(R.id.UserEmailText);

        changePhone = findViewById(R.id.ChangePhoneBttn);
        changePhone.setOnClickListener(this);

        changePass = findViewById(R.id.ChangePassBttn);
        changePass.setOnClickListener(this);

        getContact = findViewById(R.id.ContactBttn);
        getContact.setOnClickListener(this);

        home = findViewById(R.id.BackHomeBttn);
        home.setOnClickListener(this);

        logout = findViewById(R.id.LogOutBttn);
        logout.setOnClickListener(this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null)
                {
                    String userTxtName = userProfile.name;
                    String userTxtEmail = userProfile.email;

                    userName.setText(userTxtName);
                    userEmail.setText(userTxtEmail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfile.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ChangePhoneBttn:
                startActivity(new Intent(this, ChangePhone.class));
                break;

            case R.id.ChangePassBttn:
                startActivity(new Intent(this, ForgotPassword.class));
                break;

            case R.id.ContactBttn:
                startActivity(new Intent(this, ContactUs.class));
                break;

            case R.id.BackHomeBttn:
                startActivity(new Intent(this, HomeActivity.class));
                break;

            case R.id.LogOutBttn:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }
}