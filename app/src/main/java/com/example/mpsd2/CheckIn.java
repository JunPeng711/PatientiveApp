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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CheckIn extends AppCompatActivity implements View.OnClickListener {

    Button backToHomeBtn;

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        backToHomeBtn  = findViewById(R.id.BackHomeBtn);
        backToHomeBtn.setOnClickListener(this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView nameTxtView = (TextView) findViewById(R.id.viewName);
        final TextView phoneNumTxtView = (TextView) findViewById(R.id.viewPhoneNumber);
        final TextView nameLocationTxtView = (TextView) findViewById(R.id.viewLocation);
        final TextView dateTimeTxtView = (TextView) findViewById(R.id.viewDateTime);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy, hh:mm:ss a");
        String dateTime = simpleDateFormat.format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.viewDateTime);
        textViewDate.setText(dateTime);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User checkIn = snapshot.getValue(User.class);

                if(checkIn != null)
                {
                    String Name = checkIn.name;
                    String PhoneNum = checkIn.phone;

                    nameTxtView.setText(Name);
                    phoneNumTxtView.setText(PhoneNum);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CheckIn.this, "Errors", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.BackHomeBtn:
                startActivity(new Intent(this, HomeActivity.class));
                break;
        }
    }
}