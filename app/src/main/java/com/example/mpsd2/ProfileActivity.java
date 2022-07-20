package com.example.mpsd2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button homeBtn, healthEducationBtn, appointmentBtn, healthcareProfBtn;

        homeBtn = findViewById(R.id.footBtn1);
        homeBtn.setOnClickListener(this);

        appointmentBtn = findViewById(R.id.footBtn4);
        appointmentBtn.setOnClickListener(this);

        healthEducationBtn = findViewById(R.id.footBtn5);
        healthEducationBtn.setOnClickListener(this);

        healthcareProfBtn = findViewById(R.id.HealthProfBtn);
        healthcareProfBtn.setOnClickListener(this);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView nameTxtView = (TextView) findViewById(R.id.textViewName);
        final TextView ageTxtView = (TextView) findViewById(R.id.textViewAge);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null)
                {
                    String Name = userProfile.name;
                    String Age = userProfile.age;

                    nameTxtView.setText(Name);
                    ageTxtView.setText(Age);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Errors", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.footBtn1:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.footBtn4:
                startActivity(new Intent(this, AddAppointment.class));
                break;
            case R.id.footBtn5:
                startActivity(new Intent(this, HealthEducation.class));
                break;
            case R.id.HealthProfBtn:
                startActivity(new Intent(this, HealthcareProfessional.class));
                break;
        }
    }
}