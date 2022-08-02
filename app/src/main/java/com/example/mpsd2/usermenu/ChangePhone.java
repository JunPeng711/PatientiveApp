package com.example.mpsd2.usermenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PathEffect;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mpsd2.R;
import com.example.mpsd2.User;
import com.example.mpsd2.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangePhone extends AppCompatActivity implements View.OnClickListener{

    private EditText phoneEdit;
    private Button update;

    public String phoneTxt;
    public TextView phoneNum;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);

        phoneEdit = findViewById(R.id.phoneEditText);

        update = findViewById(R.id.updateBttn);
        update.setOnClickListener(this);

        phoneNum = (TextView) findViewById(R.id.phoneText);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null)
                {
                    phoneTxt = userProfile.phone;

                    phoneNum.setText(phoneTxt);
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.updateBttn:
                updateData();
                break;
        }
    }

    public void updateData(){
        String phone = phoneEdit.getText().toString().trim();

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        if(phone.isEmpty()){
            phoneEdit.setError("Please enter a phone number!");
            phoneEdit.requestFocus();
            return;
        }
        if (phone.length()>10){
            phoneEdit.setError("Please enter a valid phone!");
            phoneEdit.requestFocus();
            return;
        }
        if(!Patterns.PHONE.matcher(phone).matches()){
            phoneEdit.setError("Please enter a valid phone!");
            phoneEdit.requestFocus();
            return;
        }
        if(!phoneNum.equals(phoneEdit)){
            reference.child(userID).child("phone").setValue(phone);
            Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, UserProfile.class));
            return;
        }else{
            Toast.makeText(this, "Problem to change phone number. Try again.", Toast.LENGTH_SHORT).show();
        }
    }
}